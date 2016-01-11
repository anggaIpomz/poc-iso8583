package org.jboss;

import com.solab.iso8583.IsoMessage;
import com.solab.iso8583.MessageFactory;
import org.apache.camel.Body;
import org.junit.Assert;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;

public class BeanMessageISO8583 {

    private MessageFactory<IsoMessage> mf;

    public BeanMessageISO8583() {
        System.out.print("Iso Bean called");
    }

    public void init() throws IOException {
        mf = new MessageFactory<>();
        mf.setCharacterEncoding("UTF-8");
        mf.setCustomField(48, new CustomField48());
        mf.setConfigPath("iso8583/config.xml");
    }

    public byte[] marshall(@Body String msg) throws UnsupportedEncodingException, ParseException {
        IsoMessage iso = mf.parseMessage(msg.getBytes(), mf.getIsoHeader(0x210).length());
        Assert.assertEquals(0x210, iso.getType());
        byte[] b = iso.writeData();
        return b;
    }

}
