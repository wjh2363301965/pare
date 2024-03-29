package com.jianhaoweb.service.risk.impl;

import com.jianhaoweb.service.risk.IRiskService;
import com.jianhaoweb.service.risk.RiskConfig;
import com.jianhaoweb.service.risk.RiskSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//@Service
@Slf4j
public class RiskService  implements IRiskService {

    private String logstashPath = "/opt/";




    @Override
    public Object restartLogstashForNewDatabase(RiskSource riskSource) {
        try {
            // 第一步：修改文件内容这一步已经测试通过
            RiskConfig conifg = new RiskConfig(riskSource);
            conifg.writeDeConfig(logstashPath+"config/","mysql-ls.conf");

            // 第二步：结束logstash进程
   /*         killLogstashProcess();

            // 第三步：启动logstash
            String command = logstashPath + "/bin/logstash -f config/mysql-ls.conf";
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            int exitValue = process.exitValue();
            if (exitValue != 0) {
                throw new RuntimeException("Failed to start logstash, exit code: " + exitValue);
            }*/
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return "Error occurred: " + e.getMessage();
        }
        return null;
    }

    public void killLogstashProcess() {
        try {
            // 获取logstash的PID
            Process process = Runtime.getRuntime().exec("pgrep -f logstash");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String pid = reader.readLine();

            if (pid != null) {
                // 结束logstash进程
                String command = "kill " + pid;
                process = Runtime.getRuntime().exec(command);
                process.waitFor();
                int exitValue = process.exitValue();
                if (exitValue != 0) {
                    throw new RuntimeException("Failed to kill logstash process, exit code: " + exitValue);
                }
            }
        } catch (Exception e) {
            log.error("Error killing logstash process", e);
            throw new RuntimeException(e);
        }
    }


    public void moveFile(String sourcePath, String destinationPath) {
        try {
            String command = "mv " + sourcePath + " " + destinationPath;
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            int exitValue = process.exitValue();
            if (exitValue != 0) {
                throw new RuntimeException("Failed to move file, exit code: " + exitValue);
            }
        } catch (Exception e) {
            log.error("Error moving file", e);
            throw new RuntimeException(e);
        }
    }



  /*  *//**
     * 执行一个sh命令
     *
     * @param shell
     * @return
     *//*
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

    *//**
     * 执行一个sh命令
     *
     * @param shell
     * @return
     *//*
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
    }*/

}
