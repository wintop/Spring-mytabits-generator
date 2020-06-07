package net.hyjuki.smgen.model;

public class DbConnConfig {
    private String driver;
    private String url;
    private String user;
    private String password;
    private String tableCat;

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTableCat() {
        return tableCat;
    }

    public void setTableCat(String tableCat) {
        this.tableCat = tableCat;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DbConnConfig{");
        sb.append("driver='").append(driver).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", user='").append(user).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", tableCat='").append(tableCat).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
