package com.dhcc.piccbid.web.rest.file;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.dhcc.framework.utils.StringUtils;

public class FileResponse extends HashMap<String, Object> {
    private Map<String, Object> msgMap = new HashMap<>();

    /*
     * Annotation:
     * {
            "uploaded": 0,
            "error": {
                "message": "失败原因"
            }
        }
     *
     * @Author: Adam Ming
     * @Date: Jan 22, 2019 at 3:49:40 PM
     */
    public String error(int code, String msg) {
        msgMap.put("message", msg);

        FileResponse result = new FileResponse();
        result.put("uploaded", code);
        result.put("error", msgMap);

        System.out.println(beanToJson(result));

        return beanToJson(result);
    }

    /*
     * Annotation:
     * {
            "uploaded": 1,
            "fileName": "foo(2).jpg",
            "url": "/files/foo(2).jpg",
            "error": {
                "message": "A file with the same name already exists. The uploaded file was renamed to \"foo(2).jpg\"."
            }
        }
     *
     * @Author: Adam Ming
     * @Date: Jan 22, 2019 at 4:02:09 PM
     */
    public String success(int code, String fileName, String url, String msg) {
        FileResponse result = new FileResponse();

        result.put("uploaded", code);
        result.put("fileName", fileName);
        result.put("url", url);
        if (!StringUtils.isNullOrEmpty(msg)) {
            msgMap.put("message", msg);
            result.put("error", msgMap);
        }

        return beanToJson(result);
    }

    private String beanToJson(Map map) {
        JSONObject jsonObject = new JSONObject(map);

        return jsonObject.toString();
    }
}
