package com.jianhaoweb.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RiskService   implements IRiskService {

    private String logstashPath="/opt/dscg/logstash-8.7.1/";


    public Boolean startRiskService(RiskSource riskSource) throws Exception {
        String serviceId  =riskSource.getService();



        String cmd ="nohup "+logstashPath + "bin/logstash "+"config/ -f config/mysql-ls.conf    --path.data="+logstashPath+"data/"+serviceId+" >/dev/null 2>&1 &";
        return execSh(cmd);
    }

    public Boolean stopRiskService(RiskSource riskSource) throws Exception {
        String cmd = String.format("/opt/dscg/re/bin/jps -m | grep  %s", riskSource.getService());
        String result = execShResult(cmd);
        if (result!=null && result.length()>0) {
            String pid = result.split(" ")[0];
            return execSh("kill -15 "+pid);
        }
        return true;
    }

    @Override
    public Object restartLogstashForNewDatabase(RiskSource riskSource) {
        try {
            //清空表

            // 修改logstash中的文件
            RiskConifg conifg = new RiskConifg(riskSource);
            conifg.writeDeConfig("/opt/dscg/logstash-8.7.1/config/","mysql-ls.conf");

            // 启动logstash
            Boolean started = startRiskService(riskSource);
            if (started) {
                return "logstash 启动成功";
            } else {
                return "logstash启动失败";
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return "错误: " + e.getMessage();
        }



    }

    /**
     * 执行一个sh命令
     *
     * @param shell
     * @return
     */
    private Boolean execSh(String shell) throws Exception {
        SshExecuter sshExecuter = new SshExecuter();
        try {
            // 执行命令
            sshExecuter.exec_nohup(shell);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        } finally {
            if (sshExecuter != null) {
                try {
                    sshExecuter.close();
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }

    /**
     * 执行一个sh命令
     *
     * @param shell
     * @return
     */
    public static String execShResult(String shell) throws Exception {
        SshExecuter sshExecuter = new SshExecuter();
        try {
            // 执行命令
            String result = sshExecuter.execToString(shell);
            return result;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        } finally {
            if (sshExecuter != null) {
                try {
                    sshExecuter.close();
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }

}
