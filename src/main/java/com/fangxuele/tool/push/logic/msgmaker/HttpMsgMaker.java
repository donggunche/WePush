package com.fangxuele.tool.push.logic.msgmaker;

import com.fangxuele.tool.push.ui.form.msg.HttpMsgForm;
import org.apache.commons.compress.utils.Lists;

import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * <pre>
 * http消息加工器
 * </pre>
 *
 * @author <a href="https://github.com/rememberber">Zhou Bo</a>
 * @since 2019/7/16.
 */
public class HttpMsgMaker implements IMsgMaker {

    public static String method;
    public static String url;
    public static String body;
    public static List<HttpMsgForm.NameValueObject> paramList;
    public static List<HttpMsgForm.NameValueObject> headerList;
    public static List<HttpMsgForm.CookieObject> cookieList;

    @Override
    public void prepare() {
        method = (String) HttpMsgForm.getInstance().getMethodComboBox().getSelectedItem();
        url = HttpMsgForm.getInstance().getUrlTextField().getText().trim();
        body = HttpMsgForm.getInstance().getBodyTextArea().getText();

        // Params=========================
        if (HttpMsgForm.getInstance().getParamTable().getModel().getRowCount() == 0) {
            HttpMsgForm.initParamTable();
        }
        DefaultTableModel paramTableModel = (DefaultTableModel) HttpMsgForm.getInstance().getParamTable().getModel();
        int rowCount = paramTableModel.getRowCount();
        HttpMsgForm.NameValueObject nameValueObject;
        paramList = Lists.newArrayList();
        for (int i = 0; i < rowCount; i++) {
            String name = ((String) paramTableModel.getValueAt(i, 0)).trim();
            String value = ((String) paramTableModel.getValueAt(i, 1)).trim();
            nameValueObject = new HttpMsgForm.NameValueObject();
            nameValueObject.setName(name);
            nameValueObject.setValue(value);
            paramList.add(nameValueObject);
        }
        // Headers=========================
        if (HttpMsgForm.getInstance().getHeaderTable().getModel().getRowCount() == 0) {
            HttpMsgForm.initHeaderTable();
        }
        DefaultTableModel headerTableModel = (DefaultTableModel) HttpMsgForm.getInstance().getHeaderTable().getModel();
        rowCount = headerTableModel.getRowCount();
        headerList = Lists.newArrayList();
        for (int i = 0; i < rowCount; i++) {
            String name = ((String) headerTableModel.getValueAt(i, 0)).trim();
            String value = ((String) headerTableModel.getValueAt(i, 1)).trim();
            nameValueObject = new HttpMsgForm.NameValueObject();
            nameValueObject.setName(name);
            nameValueObject.setValue(value);
            headerList.add(nameValueObject);
        }
        // Cookies=========================
        if (HttpMsgForm.getInstance().getCookieTable().getModel().getRowCount() == 0) {
            HttpMsgForm.initCookieTable();
        }
        DefaultTableModel cookieTableModel = (DefaultTableModel) HttpMsgForm.getInstance().getCookieTable().getModel();
        rowCount = cookieTableModel.getRowCount();
        cookieList = Lists.newArrayList();
        HttpMsgForm.CookieObject cookieObject;
        for (int i = 0; i < rowCount; i++) {
            String name = ((String) cookieTableModel.getValueAt(i, 0)).trim();
            String value = ((String) cookieTableModel.getValueAt(i, 1)).trim();
            String domain = ((String) cookieTableModel.getValueAt(i, 2)).trim();
            String path = ((String) cookieTableModel.getValueAt(i, 3)).trim();
            String expiry = ((String) cookieTableModel.getValueAt(i, 4)).trim();
            cookieObject = new HttpMsgForm.CookieObject();
            cookieObject.setName(name);
            cookieObject.setValue(value);
            cookieObject.setDomain(domain);
            cookieObject.setPath(path);
            cookieObject.setExpiry(expiry);
            cookieList.add(cookieObject);
        }
    }

    @Override
    public Object makeMsg(String[] msgData) {
        return null;
    }
}
