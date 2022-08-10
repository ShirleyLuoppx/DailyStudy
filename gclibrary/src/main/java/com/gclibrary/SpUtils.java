package com.gclibrary;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SpUtils {
    private static final String PREFERENCE_NAME = "saveInfo";
    private static SharedPreferences sharedPreferences;
    private static SpUtils preference;
    private static Editor editor;

    private String ACCOUNTID = "ACCOUNTID";
    private String TOKEN = "TOKEN";
    private String NICKNAME = "NICKNAME";
    private String PASSWORD = "PASSWORD";
    private String SEX = "SEX";
    private String BIRTH = "BIRTH";
    private String IMAGE = "IMAGE";
    private String ISLOGIN = "ISLOGIN";

    private String LATITUDE = "LATITUDE";
    private String LONGITUDE = "LONGITUDE";
    private String CITY = "CITY";
    private String NEARPOSITION = "NEARPOSITION";

    private String SELECTLATITUDE = "SELECTLATITUDE";
    private String SELECTLONGITUDE = "SELECTLONGITUDE";
    private String SELECTCITY = "SELECTCITY";
    private String SELECTADDRESS = "SELECTADDRESS";


    private String PROVINCE = "PROVINCE";
    private String COUNTRY = "COUNTRY";
    private String ADDRSTR = "ADDRSTR";
    private String COMMUNITYID = "COMMUNITYID";
    private String COMMUNITYNAME = "COMMUNITYNAME";
    private String ISMINE = "ISMINE";
    private String COMMUNITYLOCATION = "COMMUNITYLOCATION";
    private String ID = "ID";
    private String COUID = "COUID";
    private String COLLECTIONID = "COLLECTIONID";
    private String NOTICESTATE = "NOTICESTATE";
    private String PRAISESTATE = "PRAISESTATE";
    private String STATMPSTATE = "STATMPSTATE";
    private String USERUNREAD = "USERUNREAD";

    private String DECISIONOWNVOTESUPPORT = "DECISIONOWNVOTESUPPORT";
    private String DECISIONOWNVOTENOSUPPORT = "DECISIONOWNVOTENOSUPPORT";
    private String GROUPID = "GROUPID";
    private String GROUPTYPE = "GROUPTYPE";

    private String ISNEWNOTICE = "ISNEWNOTICE";
    private String ISNEWHELP = "ISNEWHELP";
    private String ISNEWSERVICE = "ISNEWSERVICE";
    private String ISNEWVOTE = "ISNEWVOTE";
    private String DOWNAPPURL = "DOWNAPPURL";

    private String FIGHTTYPE = "FIGHTTYPE";

    private String STOREID = "STOREID";
    private String STOREIMAGE = "STOREIMAGE";
    private String ORDERTYPE = "ORDERTYPE";//0买油订单 1团购订单 2直营店底单
    private String ORDERNUM = "ORDERNUM";//订单

    private String COOKIE = "COOKIE";
    private String CLIENTID = "CLIENTID";
    private String USERID = "USERID";
    private String DLSID = "DLSID";
    private String USERTYPE = "USERTYPE";
    private String ISFIRSTENTER = "ISFIRSTENTER";
    private String BOOKSHOWNOTICE = "BOOKSHOWNOTICE";
    private String BOOKSET = "BOOKSET";
    private String BOOKNOTEFLOAT = "BOOKNOTEFLOAT";
    private String WELCOMEGUIDE = "WELCOMEGUIDE";
    private String OFFSWITCH = "OFFSWITCH";
    private String FRISTSEEPRIVATE = "FRISTSEEPRIVATE";


    private SpUtils(Context cxt) {
        sharedPreferences = cxt.getSharedPreferences(PREFERENCE_NAME,
                Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static void init(Context cxt) {
        if (preference == null)
            preference = new SpUtils(cxt);
    }

    public static SpUtils getInstance() {
        return preference;
    }

    public String getCustomUrl(String key) {
        return sharedPreferences.getString(key, "");
    }

    public void setCustomUrl(String key, String vaule) {
        editor.putString(key, vaule);
        editor.commit();
    }

    public boolean getCustomUrlBool(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public void setCustomUrl(String key, boolean vaule) {
        editor.putBoolean(key, vaule);
        editor.commit();
    }

    public long getCustomUrlLong(String key) {
        return sharedPreferences.getLong(key, 0);
    }

    public void setCustomUrl(String key, long vaule) {
        editor.putLong(key, vaule);
        editor.commit();
    }


    public Boolean getFristSeePrivate() {
        return sharedPreferences.getBoolean(FRISTSEEPRIVATE, true);
    }

    public void setFristSeePrivate(Boolean fristSeePrivate) {
        editor.putBoolean(FRISTSEEPRIVATE, fristSeePrivate);
        editor.commit();
    }


    public boolean getOffSwitch() {
        return sharedPreferences.getBoolean(OFFSWITCH, false);
    }

    public void setOffSwitch(boolean offSwitch) {
        editor.putBoolean(OFFSWITCH, offSwitch);
        editor.commit();
    }

    public boolean getWelcomeGuide() {
        return sharedPreferences.getBoolean(WELCOMEGUIDE, true);
    }

    public void setWelcomeGuide(boolean welcomeGuide) {
        editor.putBoolean(WELCOMEGUIDE, welcomeGuide);
        editor.commit();
    }

    public boolean getBookNoteFloat() {
        return sharedPreferences.getBoolean(BOOKNOTEFLOAT, true);
    }

    public void setBookNoteFloat(boolean bookNoteFloat) {
        editor.putBoolean(BOOKNOTEFLOAT, bookNoteFloat);
        editor.commit();
    }

    public int getBookSet() {
        return sharedPreferences.getInt(BOOKSET, 0);
    }

    public void setBookSet(int bookSet) {
        editor.putInt(BOOKSET, bookSet);
        editor.commit();
    }

    public int getBookShowNotice() {
        return sharedPreferences.getInt(BOOKSHOWNOTICE, 5);
    }

    public void setBookShowNotice(int bookShowNotice) {
        editor.putInt(BOOKSHOWNOTICE, bookShowNotice);
        editor.commit();
    }

    public void setIsFirstEnter(boolean isFirstEnter) {
        editor.putBoolean(ISFIRSTENTER, isFirstEnter);
        editor.commit();
    }

    public boolean getIsFirstEnter() {
        return sharedPreferences.getBoolean(ISFIRSTENTER, true);
    }

    public String getUserID() {
        return sharedPreferences.getString(USERID, "");
    }

    public void setUserID(String userID) {
        editor.putString(USERID, userID);
        editor.commit();
    }

    public String getDlsID() {
        return sharedPreferences.getString(DLSID, "");
    }

    public void setDlsID(String dlsID) {
        editor.putString(DLSID, dlsID);
        editor.commit();
    }


    public String getClientID() {
        return sharedPreferences.getString(CLIENTID, "");
    }

    public void setClientID(String clientid) {
        editor.putString(CLIENTID, clientid);
        editor.commit();
    }

    public String getCookie() {
        return sharedPreferences.getString(COOKIE, "");
    }

    public void setCookie(String cookie) {
        editor.putString(COOKIE, cookie);
        editor.commit();
    }

    public String getOrderNum() {
        return sharedPreferences.getString(ORDERNUM, "");
    }

    public void setOrderNum(String orderNum) {
        editor.putString(ORDERNUM, orderNum);
        editor.commit();
    }


    public int getOrderType() {
        return sharedPreferences.getInt(ORDERTYPE, -1);
    }

    public void setOrderType(int orderType) {
        editor.putInt(ORDERTYPE, orderType);
        editor.commit();
    }

    public String getStoreImage() {
        return sharedPreferences.getString(STOREIMAGE, "");
    }

    public void setStoreImage(String storeImage) {
        editor.putString(STOREIMAGE, storeImage);
        editor.commit();
    }

    public Long getStoreId() {
        return sharedPreferences.getLong(STOREID, 0);
    }

    public void setStoreId(Long storeId) {
        editor.putLong(STOREID, storeId);
        editor.commit();
    }

    public String getAccountId() {

        return sharedPreferences.getString(ACCOUNTID, "");
    }

    public void setAccountId(String accountId) {
        editor.putString(ACCOUNTID, accountId);
        editor.commit();
    }

    public String getToken() {

        return sharedPreferences.getString(TOKEN, "");
    }

    public void setToken(String token) {
        editor.putString(TOKEN, token);
        editor.commit();
    }

    public String getNickName() {
        return sharedPreferences.getString(NICKNAME, "");
    }

    public void setNickName(String nickName) {
        editor.putString(NICKNAME, nickName);
        editor.commit();
    }

    public String getPassword() {
        return sharedPreferences.getString(PASSWORD, "");
    }

    public void setPassword(String password) {
        editor.putString(PASSWORD, password);
        editor.commit();
    }

    public double getLat() {
        return (double) sharedPreferences.getFloat(LATITUDE, 0);
    }

    public void setLat(double lat) {
        editor.putFloat(LATITUDE, (float) lat);
        editor.commit();
    }

    public double getLog() {
        return (double) sharedPreferences.getFloat(LONGITUDE, 0);
    }

    public void setLog(double log) {
        editor.putFloat(LONGITUDE, (float) log);
        editor.commit();
    }

    public String getBirth() {
        return sharedPreferences.getString(BIRTH, "");
    }

    public void setBirth(String birth) {
        editor.putString(BIRTH, birth);
        editor.commit();
    }

    public int getSex() {
        return sharedPreferences.getInt(SEX, 0);
    }

    public void setSex(int sex) {
        editor.putInt(SEX, sex);
        editor.commit();
    }

    public String getProvince() {
        return sharedPreferences.getString(PROVINCE, "");
    }

    public void setProvince(String province) {
        editor.putString(PROVINCE, province);
        editor.commit();
    }

    public String getCity() {
        return sharedPreferences.getString(CITY, "");
    }

    public void setCity(String city) {
        editor.putString(CITY, city);
        editor.commit();
    }

    public String getNearPosition() {
        return sharedPreferences.getString(NEARPOSITION, "");
    }

    public void setNearPosition(String nearPosition) {
        editor.putString(NEARPOSITION, nearPosition);
        editor.commit();
    }

    public String getCountry() {
        return sharedPreferences.getString(COUNTRY, "");
    }

    public void setCountry(String country) {
        editor.putString(COUNTRY, country);
        editor.commit();
    }

    public String getAddrStr() {
        return sharedPreferences.getString(ADDRSTR, "");
    }

    public void setAddrStr(String addrStr) {
        editor.putString(ADDRSTR, addrStr);
        editor.commit();
    }


    public double getSelectLat() {
        return (double) sharedPreferences.getFloat(SELECTLATITUDE, (float) getLat());
    }

    public void setSelectLat(double lat) {
        editor.putFloat(SELECTLATITUDE, (float) lat);
        editor.commit();
    }

    public double getSelectLog() {
        return (double) sharedPreferences.getFloat(SELECTLONGITUDE, (float) getLog());
    }

    public void setSelectLog(double log) {
        editor.putFloat(SELECTLONGITUDE, (float) log);
        editor.commit();
    }

    public String getSelectCity() {
        return sharedPreferences.getString(SELECTCITY, getCity());
    }

    public void setSelectCity(String city) {
        editor.putString(SELECTCITY, city);
        editor.commit();
    }


    public String getSelectAddrStr() {
        return sharedPreferences.getString(SELECTADDRESS, getAddrStr());
    }

    public void setSelectAddrStr(String addrStr) {
        editor.putString(SELECTADDRESS, addrStr);
        editor.commit();
    }


    public long getCommunityId() {
        return sharedPreferences.getLong(COMMUNITYID, 0);
    }

    public void setCommunityId(long communityId) {
        editor.putLong(COMMUNITYID, communityId);
        editor.commit();
    }

    public int getId() {
        return sharedPreferences.getInt(ID, 0);
    }

    public void setId(int id) {
        editor.putLong(ID, id);
        editor.commit();
    }

    public String getCommunityName() {
        return sharedPreferences.getString(COMMUNITYNAME, "");
    }

    public void setCommunityName(String communityName) {
        editor.putString(COMMUNITYNAME, communityName);
        editor.commit();
    }

    public String getNoticeState() {
        return sharedPreferences.getString(NOTICESTATE, "");
    }

    public void setNoticeState(String noticeState) {
        editor.putString(NOTICESTATE, noticeState);
        editor.commit();
    }

    public String getPraisestate() {
        return sharedPreferences.getString(PRAISESTATE, "");
    }

    public void setPraisestate(String praisestate) {
        editor.putString(PRAISESTATE, praisestate);
        editor.commit();
    }

    public String getStatmpstate() {
        return sharedPreferences.getString(STATMPSTATE, "");
    }

    public void setStatmpstate(String statmpstate) {
        editor.putString(STATMPSTATE, statmpstate);
        editor.commit();
    }

    public int getIsMine() {
        return sharedPreferences.getInt(ISMINE, 0);
    }

    public void setIsMine(int isMine) {
        editor.putInt(ISMINE, isMine);
        editor.commit();
    }

    public long getCouId() {
        return sharedPreferences.getLong(COUID, 0);
    }

    public void setCouId(long couId) {
        editor.putLong(COUID, couId);
        editor.commit();
    }

    public int getUserUnRead() {
        return sharedPreferences.getInt(USERUNREAD, 0);
    }

    public void setUserUnRead(int userUnRead) {
        editor.putInt(USERUNREAD, userUnRead);
        editor.commit();
    }

    public String getImage() {
        return sharedPreferences.getString(IMAGE, "");
    }

    public void setImage(String image) {
        editor.putString(IMAGE, image);
        editor.commit();
    }

    public String getCommunitylocation() {
        return sharedPreferences.getString(COMMUNITYLOCATION, "");
    }

    public void setCommunitylocation(String communitylocation) {
        editor.putString(COMMUNITYLOCATION, communitylocation);
        editor.commit();
    }

    public long getCollectionId() {
        return sharedPreferences.getLong(COLLECTIONID, 0);
    }

    public void setCollectionId(long collectionId) {
        editor.putLong(COLLECTIONID, collectionId);
        editor.commit();
    }

    public String getDecisionownvotesupport() {
        return sharedPreferences.getString(DECISIONOWNVOTESUPPORT, "");
    }

    public void setDecisionownvotesupport(String decisionownvotesupport) {
        editor.putString(DECISIONOWNVOTESUPPORT, decisionownvotesupport);
        editor.commit();
    }

    public String getDecisionownvotenosupport() {
        return sharedPreferences.getString(DECISIONOWNVOTENOSUPPORT, "");
    }

    public void setDecisionownvotenosupport(String decisionownvotenosupport) {
        editor.putString(DECISIONOWNVOTENOSUPPORT, decisionownvotenosupport);
        editor.commit();
    }

    public Long getGroupId() {
        return sharedPreferences.getLong(GROUPID, 0);
    }

    public void setGroupId(long groupId) {
        editor.putLong(GROUPID, groupId);
        editor.commit();
    }

    public int getGroupType() {
        return sharedPreferences.getInt(GROUPTYPE, 0);
    }

    public void setGroupType(int groupType) {
        editor.putInt(GROUPTYPE, groupType);
        editor.commit();
    }

    public boolean getIsNewNotice() {
        return sharedPreferences.getBoolean(ISNEWNOTICE, false);
    }

    public void setIsNewNotice(Boolean isNewNotice) {
        editor.putBoolean(ISNEWNOTICE, isNewNotice);
        editor.commit();
    }

    public boolean getIsNewHelp() {
        return sharedPreferences.getBoolean(ISNEWHELP, false);
    }

    public void setIsNewHelp(Boolean isNewHelp) {
        editor.putBoolean(ISNEWHELP, isNewHelp);
        editor.commit();
    }

    public boolean getIsNewService() {
        return sharedPreferences.getBoolean(ISNEWSERVICE, false);
    }

    public void setIsNewService(Boolean isNewService) {
        editor.putBoolean(ISNEWSERVICE, isNewService);
        editor.commit();
    }

    public boolean getIsNewVote() {
        return sharedPreferences.getBoolean(ISNEWVOTE, false);
    }

    public void setIsNewVote(Boolean isNewVote) {
        editor.putBoolean(ISNEWVOTE, isNewVote);
        editor.commit();
    }

    public String getDownAppUrl() {
        return sharedPreferences.getString(DOWNAPPURL, "");
    }

    public void setDownAppUrl(String downAppUrl) {
        editor.putString(DOWNAPPURL, downAppUrl);
        editor.commit();
    }

    public boolean getIsLogin() {
        return sharedPreferences.getBoolean(ISLOGIN, false);
    }

    public void setIsLogin(boolean isLogin) {
        editor.putBoolean(ISLOGIN, isLogin);
        editor.commit();
    }

    public int getFightType() {
        return sharedPreferences.getInt(FIGHTTYPE, 0);
    }

    public void setFightType(int fightType) {
        editor.putInt(FIGHTTYPE, fightType);
        editor.commit();
    }

    public int getUserType() {
        return sharedPreferences.getInt(USERTYPE, 1);
    }

    public void setUserType(int userType) {
        editor.putInt(USERTYPE, userType);
        editor.commit();
    }
}
