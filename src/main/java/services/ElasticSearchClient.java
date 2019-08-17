package services;

import org.apache.log4j.MDC;
import domain.json.UploadDocumentRequest;
import java.util.Random;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.apache.log4j.Logger;

public class ElasticSearchClient {

    public static final Logger logger=Logger.getLogger(ElasticSearchClient.class);

    public static void main(String[] args) throws Exception {

        ElasticSearchCommand command;
        Random r = new Random();
        int min, max;

        int cycles = 1;

        for(int i = 0; i < cycles; i++){
            min = 1;
            max = 10;
            int num = r.nextInt((max - min) + 1) + min;;
            String name = "Machine#" + num;

            min = 0;
            max = 100;
            int cpu = r.nextInt((max - min) + 1) + min;;

            min = 1000;
            max = 10000;
            int memory = r.nextInt((max - min) + 1) + min;;

            // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss.SSS");
            // String currTime = dateFormat.format(new Date());

            // command = new UploadDocument(name, cpu, memory, currTime);
            // command.execute();


            // more info on gelf loggers: https://logging.paluch.biz/examples/log4j-1.2.x.html

            // logger.debug("This is a debug message!"); // doesn't appear in logstash
            MDC.put("machineName", name);
            MDC.put("cpu", cpu);
            MDC.put("memory", memory);
            logger.info("The request message.");
            MDC.clear(); // not necessary, but it's good to be safe
            // logger.warn("This is a warn message!");
            // logger.error("This is error message!");
        }
    }
}
