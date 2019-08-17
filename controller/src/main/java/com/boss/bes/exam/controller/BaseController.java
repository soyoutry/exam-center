package com.boss.bes.exam.controller;

import protocol.CommonRequest;
import protocol.CommonResponse;

import java.util.List;

public abstract class BaseController {
    public abstract void add(CommonRequest commonRequest);
    public abstract void delete(CommonRequest commonRequest);
    public abstract void update(CommonRequest commonRequest);
    public abstract List<CommonResponse> queryRecord(CommonRequest commonRequest);
}
