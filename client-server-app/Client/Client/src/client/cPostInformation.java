package client;

import java.util.ArrayList;

public class cPostInformation {

        private short nPostID;
	private String sTitle;
	private String sDescriptionText;
        private String sShortDescription;
	
	private boolean isVIP;
	
	private boolean bToPublish;
	
	public cPostInformation(String sTitle, String sDescriptionText, String sShortDescription,
			boolean isVIP, boolean bToPublish, short nPostID)
			{
				this.sTitle = sTitle;
				this.sDescriptionText = sDescriptionText;
				this.isVIP = isVIP;
				this.bToPublish = bToPublish;
                                this.sShortDescription = sShortDescription;
                                this.nPostID = nPostID;
			}
        
        public cPostInformation(){}
	
	public String getTitle(){return this.sTitle;}
	public String getDescriptionText(){return this.sDescriptionText;}
        public String getShortDescriptionText(){return this.sShortDescription;}
	public boolean isVIP(){return this.isVIP;}
	public boolean isToPublish(){return this.bToPublish;}
	public short getPostID(){return this.nPostID;}
        
	public void setTitle(String _Title){this.sTitle = _Title;}
	public void setDescriptionText(String _Description){this.sDescriptionText = _Description;}
        public void setShortDescriptionText(String _Description){this.sShortDescription = _Description;}
	public void setVIP(boolean _isVIP){this.isVIP = _isVIP;}
	public void setDaysToPublish(boolean _DTPublish){this.bToPublish = _DTPublish;}
        public void setPostID(short _nPostID){this.nPostID = _nPostID;}
}