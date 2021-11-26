package com.luospace.geo.service;


public interface YilianyunService {
    public void init();
    public String getOpenAccessToken() throws Exception;
    public void addPrinter(String machineCode,String msign,String openAccessToken) throws Exception;
    public void printIndex() throws Exception;
}
