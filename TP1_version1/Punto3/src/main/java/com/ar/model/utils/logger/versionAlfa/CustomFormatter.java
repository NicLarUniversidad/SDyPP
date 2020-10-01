package com.ar.model.utils.logger.versionAlfa;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class CustomFormatter extends Formatter {

    @Override
    public String format(LogRecord record) {
        return  record.getLevel()+"::"
        		+"ThreadID=" + record.getThreadID()+"::"+ record.getSourceClassName()+"::"
                +record.getSourceMethodName()+"::"
                +new Date(record.getMillis())+"::\n"
                +record.getMessage()+"\n\n";
    }


}
