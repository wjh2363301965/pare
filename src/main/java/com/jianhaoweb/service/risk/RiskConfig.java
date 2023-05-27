package com.jianhaoweb.service.risk;


public class RiskConfig {

    private String lineSeparator = System.getProperty("line.separator");

    private String dbType;
    private String dbIp;
    private Integer dbPort;
    private String userName;
    private String password;

    public RiskConfig(RiskSource riskSource) {
        this.dbType = riskSource.getDbType();
        this.dbIp = riskSource.getDbIp();
        this.dbPort = riskSource.getDbPort();
        this.userName = riskSource.getUserName();
        this.password = riskSource.getPassword();
    }

    public Boolean writeDeConfig(String filePath, String fileName) {
        StringBuffer sb = new StringBuffer();
        //input
        generateInputConfig(sb);
        //filter
        generateFilterConfig(sb);
        //output
        generateOutputConfig(sb);

        Boolean aBoolean = Tools.writeStringToFile(sb.toString().getBytes(), filePath, fileName);

        return aBoolean;
    }

    private void generateInputConfig(StringBuffer sb){
        sb.append("input{");
        sb.append(lineSeparator);
        sb.append("jdbc{");
        sb.append(lineSeparator);

        sb.append("plugin_timezone => \"local\"");
        sb.append(lineSeparator);

        if ("mysql".equalsIgnoreCase(dbType)) {
            sb.append("jdbc_driver_library => \"/opt/dscg/logstash-8.7.1/lib/mysql-connector-java-5.1.34.jar\"");
            sb.append(lineSeparator);

            sb.append("jdbc_driver_class => \"com.mysql.jdbc.Driver\"");
            sb.append(lineSeparator);

            sb.append("jdbc_connection_string => \"jdbc:mysql://"+dbIp+":"+dbPort+"/mysql\"");
            sb.append(lineSeparator);

        } else if ("mariadb".equalsIgnoreCase(dbType)) {
            sb.append("jdbc_driver_library => \"/opt/dscg/logstash-8.7.1/lib/mariadb-java-client-3.1.4.jar\"");
            sb.append(lineSeparator);

            sb.append("jdbc_driver_class => \"org.mariadb.jdbc.Driver\"");
            sb.append(lineSeparator);

            sb.append("jdbc_connection_string => \"jdbc:mariadb://"+dbIp+":"+dbPort+"/mysql\"");
            sb.append(lineSeparator);

        } else {
            sb.append("jdbc_driver_library => \"/opt/dscg/logstash-8.7.1/lib/mariadb-java-client-3.1.4.jar\"");
            sb.append(lineSeparator);
        }

        sb.append("jdbc_default_timezone => \"Asia/Shanghai\"");
        sb.append(lineSeparator);

        sb.append("connection_retry_attempts => \"65535\"");
        sb.append(lineSeparator);

        sb.append("connection_retry_attempts_wait_time => \"10\"");
        sb.append(lineSeparator);

        sb.append("jdbc_validate_connection => \"true\"");
        sb.append(lineSeparator);

        sb.append("jdbc_validation_timeout => \"10\"");
        sb.append(lineSeparator);

        sb.append("jdbc_user => \""+userName+"\"");
        sb.append(lineSeparator);

        sb.append("jdbc_password => \""+password+"\"");
        sb.append(lineSeparator);

        sb.append("schedule => \"* * * * *\"");
        sb.append(lineSeparator);

        sb.append("statement => \"select event_time,user_host,command_type,argument from  mysql.general_log where argument not like 'select event_time,user_host,command_type,argument from mysql.general_log%' and event_time > :sql_last_value order by event_time asc\"");
        sb.append(lineSeparator);

        sb.append("tracking_column => \"event_time\"");
        sb.append(lineSeparator);

        sb.append("tracking_column_type => \"timestamp\"");
        sb.append(lineSeparator);

        sb.append("last_run_metadata_path => \"/home/hill/logstash-8.7.1/run/my-logstash.yml\"");
        sb.append(lineSeparator);

        sb.append("}");
        sb.append(lineSeparator);
        sb.append("}");
        sb.append(lineSeparator);
    }

    private void generateFilterConfig(StringBuffer sb){
        sb.append("output{");
        sb.append(lineSeparator);

        sb.append("grok{");
        sb.append(lineSeparator);

        sb.append("match => { \"user_host\" => \"(?<user>(?<=\\[).*?(?=\\])).*(?<ip>(?<=\\[).*?(?=\\]))\" }");
        sb.append(lineSeparator);

        sb.append("}");
        sb.append(lineSeparator);

        sb.append("if [command_type] == \"Query\" {");
        sb.append(lineSeparator);

        sb.append("grok {");
        sb.append(lineSeparator);

        sb.append("match => { \"argument\" => \"%{WORD:method}\" }");
        sb.append(lineSeparator);

        sb.append("}");
        sb.append(lineSeparator);

        sb.append("} else {");
        sb.append(lineSeparator);

        sb.append("mutate { add_field => { method => \"%{command_type}\" } }");
        sb.append(lineSeparator);

        sb.append("}");
        sb.append(lineSeparator);

        sb.append("if !([ip]) {");
        sb.append(lineSeparator);


        sb.append("mutate { add_field => { ip => \"127.0.0.1\" } }");
        sb.append(lineSeparator);

        sb.append("}");
        sb.append(lineSeparator);

        sb.append("}");
        sb.append(lineSeparator);
    }

    private void generateOutputConfig(StringBuffer sb){
        sb.append("output{");
        sb.append(lineSeparator);
        sb.append("jdbc{");
        sb.append(lineSeparator);

        sb.append("driver_jar_path => \"/opt/dscg/logstash-8.7.1/lib/mariadb-java-client-3.1.4.jar\"");
        sb.append(lineSeparator);

        sb.append("driver_class => \"org.mariadb.jdbc.Driver\"");
        sb.append(lineSeparator);

        sb.append("connection_string => \"jdbc:mariadb://127.0.0.1:3066/sailing?user=dscg&password=sailing2018!\"");
        sb.append(lineSeparator);

        sb.append("statement => [ \"INSERT INTO sailing.tbl_dbms_operate_log(user,ip,command,method,event_time,content) VALUES (?,?,?,?,?,?)\",\"[user]\",\"[ip]\",\"[command_type]\",\"[method]\",\"[event_time]\",\"[argument]\" ]");
        sb.append(lineSeparator);

        sb.append("}");
        sb.append(lineSeparator);
        sb.append("}");
        sb.append(lineSeparator);
    }

}
