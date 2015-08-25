package com.artesanik.zimbramail;

public class MailAccount {
	private long id;
	private String description;
	private String account;
	private String password;
	private String sign;
	private String server;
	private String default_account;
	private int order;
	private String status;
	private String lastsync;

	//Empty constructor
	public MailAccount(){
		
	}
	
	// constructor
    public MailAccount(int id, String description, String account){
        this.id = id;
        this.description = description;
        this.account = account;
    }
	
	// constructor
    public MailAccount(int id, String description, String account, String password, String sign, String servername, String default_account, int order, String status, String lastsync){
        this.id = id;
        this.description = description;
        this.account = account;
        this.password = password;
        this.sign = sign;
        this.server = servername;
        this.default_account = default_account;
        this.order = order;
        this.status = status;
        this.lastsync = lastsync;
    }
 
    // constructor
    public MailAccount(String description, String account, String password, String sign, String servername, String default_account, int order, String status, String lastsync){
        this.description = description;
        this.account = account;
        this.password = password;
        this.sign = sign;
        this.server = servername;
        this.default_account = default_account;
        this.order = order;
        this.status = status;
        this.lastsync = lastsync;
    }
    
    public long getId() {
  	  return id;
  	}
  	
  	public void setId(long id) {
  		this.id = id;
  	}

  	public String getDescription() {
  		return description;
  	}

  	public void setDescription(String description) {
  		this.description = description;
  	}

  	public String getAccount() {
  		return account;
  	}

  	public void setAccount(String account) {
  		this.account = account;
  	}

  	public String getPassword() {
  		return password;
  	}

  	public void setPassword(String password) {
  		this.password = password;
  	}

  	public String getSign() {
  		return sign;
  	}

  	public void setSign(String sign) {
  		this.sign = sign;
  	}

  	public String getServer() {
  		return server;
  	}

  	public void setServer(String server) {
  		this.server = server;
  	}

  	public String getDefault_account() {
  		return default_account;
  	}

  	public void setDefault_account(String default_account) {
  		this.default_account = default_account;
  	}

  	public int getOrder() {
  		return order;
  	}

  	public void setOrder(int order) {
  		this.order = order;
  	}

  	public String getStatus() {
  		return status;
  	}

  	public void setStatus(String status) {
  		this.status = status;
  	}

  	public String getLastsync() {
  		return lastsync;
  	}

  	public void setLastsync(String lastsync) {
  		this.lastsync = lastsync;
  	}
}
