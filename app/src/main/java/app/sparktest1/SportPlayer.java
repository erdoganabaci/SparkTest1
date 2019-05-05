package app.sparktest1;

public class SportPlayer {
    private int playerId;
    private String playerName;
    private String playerSurname;
    private String playerBirthday;
    private String playerTckNo;
    private String playerPhone;
    private String playerClub;
    private String playerLicenseNo;
    private String playerCurrentDate;
    private String playerHeight;
    private String playerWeight;
    private String playerPeriodicValue;
    private String playerPeriodicValueType;

    public String getPlayerPeriodicValue() {
        return playerPeriodicValue;
    }

    public void setPlayerPeriodicValue(String playerPeriodicValue) {
        this.playerPeriodicValue = playerPeriodicValue;
    }

    public String getPlayerPeriodicValueType() {
        return playerPeriodicValueType;
    }

    public void setPlayerPeriodicValueType(String playerPeriodicValueType) {
        this.playerPeriodicValueType = playerPeriodicValueType;
    }

    public SportPlayer(int playerId, String playerName, String playerSurname, String playerBirthday, String playerTckNo, String playerPhone, String playerClub, String playerLicenseNo, String playerCurrentDate, String playerHeight, String playerWeight) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.playerSurname = playerSurname;
        this.playerBirthday = playerBirthday;
        this.playerTckNo = playerTckNo;
        this.playerPhone = playerPhone;
        this.playerClub = playerClub;
        this.playerLicenseNo = playerLicenseNo;
        this.playerCurrentDate = playerCurrentDate;
        this.playerHeight = playerHeight;
        this.playerWeight = playerWeight;

    }
    public SportPlayer(int playerId, String playerName, String playerSurname, String playerBirthday, String playerTckNo, String playerPhone, String playerClub, String playerLicenseNo) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.playerSurname = playerSurname;
        this.playerBirthday = playerBirthday;
        this.playerTckNo = playerTckNo;
        this.playerPhone = playerPhone;
        this.playerClub = playerClub;
        this.playerLicenseNo = playerLicenseNo;
    }
    /*
    public SportPlayer(String playerName, String playerSurname, String playerBirthday, String playerTckNo, String playerPhone, String playerClub, String playerLicenseNo, String playerCurrentDate, String playerHeight, String playerWeight) {
        this.playerName = playerName;
        this.playerSurname = playerSurname;
        this.playerBirthday = playerBirthday;
        this.playerTckNo = playerTckNo;
        this.playerPhone = playerPhone;
        this.playerClub = playerClub;
        this.playerLicenseNo = playerLicenseNo;
        this.playerCurrentDate = playerCurrentDate;
        this.playerHeight = playerHeight;
        this.playerWeight = playerWeight;

    } */
    public SportPlayer(String playerName, String playerSurname, String playerBirthday, String playerTckNo, String playerPhone, String playerClub, String playerLicenseNo, String playerCurrentDate, String playerHeight, String playerWeight,String playerPeriodicValue,String  playerPeriodicValueType) {
        this.playerName = playerName;
        this.playerSurname = playerSurname;
        this.playerBirthday = playerBirthday;
        this.playerTckNo = playerTckNo;
        this.playerPhone = playerPhone;
        this.playerClub = playerClub;
        this.playerLicenseNo = playerLicenseNo;
        this.playerCurrentDate = playerCurrentDate;
        this.playerHeight = playerHeight;
        this.playerWeight = playerWeight;
        this.playerPeriodicValue = playerPeriodicValue;
        this.playerPeriodicValueType =  playerPeriodicValueType;
    }
    public SportPlayer(String playerName, String playerSurname, String playerBirthday, String playerTckNo, String playerPhone, String playerClub, String playerLicenseNo, String playerCurrentDate,String playerPeriodicValue,String  playerPeriodicValueType) {
        this.playerName = playerName;
        this.playerSurname = playerSurname;
        this.playerBirthday = playerBirthday;
        this.playerTckNo = playerTckNo;
        this.playerPhone = playerPhone;
        this.playerClub = playerClub;
        this.playerLicenseNo = playerLicenseNo;
        this.playerCurrentDate = playerCurrentDate;
        this.playerPeriodicValue = playerPeriodicValue;
        this.playerPeriodicValueType =  playerPeriodicValueType;
    }


    public SportPlayer(String playerName,String playerSurname ,int playerId){
        this.playerName = playerName;
        this.playerSurname = playerSurname;
        this.playerId = playerId;
    }
    public SportPlayer(String playerCurrentDate,String playerPeriodicValue,String playerPeriodicValueType ){
        this.playerCurrentDate = playerCurrentDate;
        this.playerPeriodicValue = playerPeriodicValue;
        this.playerPeriodicValueType = playerPeriodicValueType;
    }
    public SportPlayer(String playerName){
        this.playerName = playerName;

    }

    public SportPlayer(int playerId){
        this.playerId = playerId;

    }
    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerSurname() {
        return playerSurname;
    }

    public void setPlayerSurname(String playerSurname) {
        this.playerSurname = playerSurname;
    }

    public String getPlayerBirthday() {
        return playerBirthday;
    }

    public void setPlayerBirthday(String playerBirthday) {
        this.playerBirthday = playerBirthday;
    }

    public String getPlayerTckNo() {
        return playerTckNo;
    }

    public void setPlayerTckNo(String playerTckNo) {
        this.playerTckNo = playerTckNo;
    }

    public String getPlayerPhone() {
        return playerPhone;
    }

    public void setPlayerPhone(String playerPhone) {
        this.playerPhone = playerPhone;
    }

    public String getPlayerClub() {
        return playerClub;
    }

    public void setPlayerClub(String playerClub) {
        this.playerClub = playerClub;
    }

    public String getPlayerLicenseNo() {
        return playerLicenseNo;
    }

    public void setPlayerLicenseNo(String playerLicenseNo) {
        this.playerLicenseNo = playerLicenseNo;
    }

    public String getPlayerCurrentDate() {
        return playerCurrentDate;
    }

    public void setPlayerCurrentDate(String playerCurrentDate) {
        this.playerCurrentDate = playerCurrentDate;
    }

    public String getPlayerHeight() {
        return playerHeight;
    }

    public void setPlayerHeight(String playerHeight) {
        this.playerHeight = playerHeight;
    }

    public String getPlayerWeight() {
        return playerWeight;
    }

    public void setPlayerWeight(String playerWeight) {
        this.playerWeight = playerWeight;
    }


}
