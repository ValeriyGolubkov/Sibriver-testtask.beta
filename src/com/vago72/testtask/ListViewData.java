package com.vago72.testtask;

/**
 * <pre>
 * <b>������� �������.</b>
 * ����� ������ ��� ������������ ������ Job-�������.
 * ����� ���������� ��������� json-������ �������� job-������� 
 *
 * ������ ������� <a href="http://testtask.beta.sibriver.com/">
 * RESPONSE DESCRIPTION</a> ��� ����� ������� ������������� � ������.
 *
 * </pre>
 * @author Valeriy Golubkov
 */
public class ListViewData {

    public Integer id;    
    public String name;     
    public Integer status; 
    public String address;    
    public String lat;    
    public String lon;    
    public String created;
    
    /**
     * ���� �������� ��������� ��������
     */
    public boolean chkVal;

    /**
     * <pre>
     * � ��������� ���������� �� ������������.
     * 
     * ������ �� �������� (MainActivity). 
     * ��������� ��� ����������� ��������� � ������� � �������� ������ ��������.
     * (��� ������ �������� ������������� � ����������� �� �������� ������� ������) 
     * </pre>
     */
    private MainActivity _MainActivity;

    /**
     * �����������
     * @param _m ������ �� ���������� ����� (��������)
     */
    public ListViewData(MainActivity _m) {
        _MainActivity = _m;
    }
    
    /**
     * ���������� �������� ResponceJob � ������ ���������
     * @param rsp ����������� ������� ResponceJob 
     */
    public void put(ResponceJob rsp) {

        this.id = rsp.id;    
        this.name = rsp.name;     
        this.status = rsp.status; 
        this.address = rsp.address;    
        this.lat = rsp.lat;    
        this.lon = rsp.lon;    
        this.created = rsp.created;
        
    }


};
