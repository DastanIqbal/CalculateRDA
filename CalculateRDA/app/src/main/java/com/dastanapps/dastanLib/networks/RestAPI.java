package com.dastanapps.dastanLib.networks;

/**
 * Created by IQBAL-MEBELKART on 8/16/2016.
 */

public class RestAPI {
    private static String BASE_URL = "http://192.168.0.11/";
    private static String MAIL_PATH = "/api/";

    public static String GET_RDA = BASE_URL + MAIL_PATH + "getRDA";
    public static int ID_GET_RDA = 1001;
}
