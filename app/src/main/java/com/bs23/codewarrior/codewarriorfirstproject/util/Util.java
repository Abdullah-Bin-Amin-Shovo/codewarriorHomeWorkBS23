package com.bs23.codewarrior.codewarriorfirstproject.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;

import retrofit.client.Response;
import retrofit.mime.MimeUtil;
import retrofit.mime.TypedByteArray;
import retrofit.mime.TypedInput;

public class Util {
    public StringBuilder GetStringFromTypedInput(TypedInput body) {
        StringBuilder responseBody = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(body.in()));
            String newLine = System.getProperty("line.separator");
            String line;
            while ((line = reader.readLine()) != null) {
                responseBody.append(line);
                responseBody.append(newLine);
            }

            // Prints the correct String representation of body.
            System.out.println(responseBody);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return responseBody;
    }
}
