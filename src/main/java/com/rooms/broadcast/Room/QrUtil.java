package com.rooms.broadcast.Room;

import net.glxn.qrgen.javase.QRCode;
import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayOutputStream;

public class QrUtil {

    public static byte[] getQrBase64FromId(String id) {
        ByteArrayOutputStream stream = QRCode
                .from(id)
                .stream();
        return Base64.encodeBase64(stream.toByteArray());
    }

}
