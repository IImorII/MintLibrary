package util;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ImageUploadUtil {
    public static void upload(String path, List<Part> parts) throws IOException {

        // creates the save directory if it does not exists
        File fileSaveDir = new File(path);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }

        String fileName;
        //Get all the parts from request and write it to the file on server
        for (Part part : parts) {
            fileName = getFileName(part);
            System.out.println(fileName);
            part.write(path + File.separator + fileName);
        }
    }

    private static String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= "+contentDisp);
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return "";
    }
}
