package base;

import config.PropertyUtil;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseApi {
    protected final RequestSpecification requestSpecification;
    public BaseApi() {
        requestSpecification = given().baseUri(PropertyUtil.getConfig().baseURI()).
                                      filters(new RequestLoggingFilter(),new ResponseLoggingFilter()).
                                      filter(new AllureRestAssured());
    }
    protected Response postRequest(String basePath,Object payload){
        return setBasePath(basePath).setContentType(ContentType.JSON).setRequestBody(payload).sendRequest(Method.POST);
    }
    protected Response getRequest(String basePath){
        return setBasePath(basePath).setContentType(ContentType.JSON).sendRequest(Method.GET);

    }
    protected Response deleteRequest(String basePath,String paramName, Object paramValue){
        return setBasePath(basePath).setHeaders().setPathParam(paramName,paramValue).sendRequest(Method.DELETE);
    }
    protected Response getRequest(String basePath,String paramName,Object paramValue){
        return setBasePath(basePath).setContentType(ContentType.JSON).setPathParam(paramName,paramValue).sendRequest(Method.GET);

    }
    protected Response putRequest(String basePath,Object payload,String paramName,Object paramValue){
        return setBasePath(basePath).setContentType(ContentType.JSON).setAuth().
                                    setRequestBody(payload).setPathParam(paramName,paramValue).sendRequest(Method.PUT);
    }
    private BaseApi setBasePath(String basePath){
        requestSpecification.basePath(basePath);
        return this;
    }
    private BaseApi setHeaders(){
        requestSpecification.header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=");
        return this;
    }
    private BaseApi setPathParam(String paramName,Object value){
        requestSpecification.pathParam(paramName,value);
        return this;
    }
    private BaseApi setRequestBody(Object object){
        requestSpecification.body(object);
        return this;
    }
    private BaseApi setContentType(ContentType contentType){
        requestSpecification.contentType(contentType);
        return this;
    }
    //auth().preemptive().basic("admin","password123").
    private BaseApi setAuth(){
        requestSpecification.auth().preemptive().basic("admin","password123");
        return this;
    }
    private Response sendRequest(Method method){
        return switch (method){
            case GET -> requestSpecification.when().get();
            case POST -> requestSpecification.when().post();
            case PUT -> requestSpecification.when().put();
            case DELETE -> requestSpecification.when().delete();
            default -> throw new IllegalArgumentException("Input Method Type not supported");
        };
    }
}
