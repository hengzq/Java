package cn.hengzq.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

    @Tool(description = "按城市名称获取天气信息")
    public String getWeather(@ToolParam(description = "城市名称，例如：西安") String cityName) {
        logger.info("获取天气信息，城市名称：{}", cityName);
        // 这里模拟天气信息查询，实际应用中需要调用天气API获取天气信息
        return "当前城市：" + cityName + "\n" +
                "天气状况：晴天转多云 \n" +
                "风力：西北风，阵风6-7级 \n" +
                "气温：9℃～27℃，昼夜温差较大 \n";
    }
}