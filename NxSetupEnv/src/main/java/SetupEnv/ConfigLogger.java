package SetupEnv;

/**
 * Created by root on 1/8/17.
 */


import org.apache.logging.log4j.*;
import org.apache.logging.log4j.core.*;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.Level;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.Writer;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigLogger {

    public static void setRootLevelDebug(){
        // false=multiple objects, true=single object
        LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        Configuration config = ctx.getConfiguration();
        LoggerConfig loggerConfig = config.getLoggerConfig(LogManager.ROOT_LOGGER_NAME);
        loggerConfig.setLevel(Level.DEBUG);
        ctx.updateLoggers();

    }

    public static void updateXmlConfig(String appName,
                                       boolean debug,
                                       String logDirectory,
                                       String rollingFileSize,
                                       String patternString,
                                       boolean deleteTmpLog4j2Config) {

        String tmpPath = System.getProperty("java.io.tmpdir");
        String log4j2ConfigPath = tmpPath + "/log4j2ConfigDirectory";
        File fp = new File(log4j2ConfigPath);
        if (!fp.exists()) {
            fp.mkdirs();
        }
        String log4j2ConfigName = log4j2ConfigPath + "/log4j2.xml";
        File log4j2Fp = new File(log4j2ConfigName);
        if (log4j2Fp.exists()) {
            log4j2Fp.delete();
        }

//        String patternString = "[%d{yyyy.MM.dd 'at' HH:mm:ss z}] %-5level : %msg%xEx%n";

        Document document = DocumentHelper.createDocument();
        Element rootElement = document.addElement("configuration");
        rootElement.addAttribute("status", "OFF");
        Element appendersElement = rootElement.addElement("appenders");
        Element loggersElement = rootElement.addElement("loggers");
        Element consoleAppenderElement = appendersElement.addElement("Console");
        consoleAppenderElement.addAttribute("name", "Console");
        consoleAppenderElement.addAttribute("target", "SYSTEM_OUT");
        Element patternLayoutOfConsoleAppender = consoleAppenderElement.addElement("PatternLayout");
//        patternLayoutOfConsoleAppender.addAttribute("pattern", "[%d{yyyy.MM.dd 'at' HH:mm:ss z}] %-5level : %msg%xEx%n");
        patternLayoutOfConsoleAppender.addAttribute("pattern",patternString);

        Element rootLoggerElement = loggersElement.addElement("root");
        if (debug){
            rootLoggerElement.addAttribute("level", "debug");
        }else {
            rootLoggerElement.addAttribute("level", "info");
        }

        Element consoleAppenderRefOfRootLogger = rootLoggerElement.addElement("appender-ref");
        consoleAppenderRefOfRootLogger.addAttribute("ref", "Console");


        if (logDirectory != null) {
            Element rollingFileAppenderElement = appendersElement.addElement("RollingFile");
            rollingFileAppenderElement.addAttribute("name", "RollingFile");
//            rollingFileAppenderElement.addAttribute("fileName", "logs/NxAssistanT.log");
            rollingFileAppenderElement.addAttribute("fileName", logDirectory+"/"+appName+".log");
//            rollingFileAppenderElement.addAttribute("filePattern", "logs/$${date:yyyy-MM}/NxAssistanT-%d{MM-dd-yyyy}-%i.log.gz");
            rollingFileAppenderElement.addAttribute("filePattern", logDirectory+"/$${date:yyyy-MM}/"+appName+"-%d{MM-dd-yyyy}-%i.log.gz");
            Element patternLayoutOfRollingFileAppenderElement = rollingFileAppenderElement.addElement("PatternLayout");
//            patternLayoutOfRollingFileAppenderElement.addAttribute("pattern", "[%d{yyyy.MM.dd 'at' HH:mm:ss z}] %-5level : %msg%xEx%n");
            patternLayoutOfRollingFileAppenderElement.addAttribute("pattern", patternString);
            Element sizeBasedPolicyOfRollingFileAppenderElement = rollingFileAppenderElement.addElement("SizeBasedTriggeringPolicy");
//            sizeBasedPolicyOfRollingFileAppenderElement.addAttribute("size", "100 MB");
            sizeBasedPolicyOfRollingFileAppenderElement.addAttribute("size", rollingFileSize);

            Element rollingFileAppenderRefOfRootLogger = rootLoggerElement.addElement("appender-ref");
            rollingFileAppenderRefOfRootLogger.addAttribute("ref", "RollingFile");
        }

        Writer fileWriter = null;
        try {
            fileWriter = new FileWriter(log4j2ConfigName);
            XMLWriter xmlWriter = new XMLWriter(fileWriter);
            xmlWriter.write(document);
            xmlWriter.flush();
            xmlWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        LoggerContext context = (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);
        File ConfigFile = new File(log4j2ConfigName);

        // this will force a reconfiguration
        context.setConfigLocation(ConfigFile.toURI());
        if (deleteTmpLog4j2Config){
            File delFp = new File(log4j2ConfigName);
            if (delFp.exists()){
                delFp.delete();
            }
        }

    }

    public static void updateXmlConfig(String appName, boolean debug, String logDirectory){
        updateXmlConfig(appName, debug, logDirectory,
                "100 MB",
                "[%d{yyyy.MM.dd 'at' HH:mm:ss z}] %-5level : %msg%xEx%n",
                true);
    }



}

