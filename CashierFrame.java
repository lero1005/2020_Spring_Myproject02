import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by lero on 2020/5/22.
 */
public class CashierFrame extends JFrame {

    private double total = 0.0;
    private int totCredit = 0;

    public static final int TEXT_ROWS = 10;
    public static final int TEXT_COLUMNS = 20;

    private JTextField priceField;
    private JTextField numField;
    private JTextField resField;
    private JTextArea listArea;
    private JButton submitButton;
    private JButton resetButton;
    private JComboBox<String> typeBox;

    public void init() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension ScreenSize = kit.getScreenSize();
        int ScreenWidth = ScreenSize.width;
        int ScreenHeight = ScreenSize.height;
        pack();
        int width = this.getWidth();
        int height = this.getHeight();
        this.setLocation((ScreenWidth - width) / 2, (ScreenHeight - height) / 2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public CashierFrame() {


        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        ActionListener submitListener = event -> submit();
        ActionListener resetListener = event -> reset();

        //construct components

        JLabel priceLabel = new JLabel("价格:");
        priceField = new JTextField(10);
        priceField.setText("0");

        submitButton = new JButton("提交");
        submitButton.addActionListener(submitListener);

        JLabel numLabel = new JLabel("数量:");
        numField = new JTextField(10);
        numField.setText("0");

        resetButton = new JButton("重置");
        resetButton.addActionListener(resetListener);

        JLabel typeLabel = new JLabel("计算方法:");

        typeBox = new JComboBox<>(new String[]{"正常收费","打七折","满100减10","VIP用户六折优惠"});

        listArea = new JTextArea(TEXT_ROWS, TEXT_COLUMNS);
        listArea.setLineWrap(true);
        listArea.setEditable(false);

        JLabel resLabel = new JLabel("总计:");
        resField = new JTextField(8);
        resField.setText(Double.toString(total));
        resField.setEnabled(false);

        add(priceLabel, new GBC(0, 0));
        add(priceField, new GBC(1,0));
        add(submitButton, new GBC(3,0));

        add(numLabel, new GBC(0,1));
        add(numField,new GBC(1,1));
        add(resetButton, new GBC(3,1));

        add(typeLabel, new GBC(0,2));
        add(typeBox, new GBC(1,2));

        add(listArea, new GBC(0,3,4,5));

        add(resLabel, new GBC(0,8));
        add(resField, new GBC(1,8));
        init();
    }

    public String formatParsing(double value) {
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df.format(value);
    }

    public void submit() {
        String type = (String) typeBox.getSelectedItem();
        CashContext cashContext = new CashContext(type);

        double price = 0.0;
        try{
            price = Double.parseDouble(priceField.getText());
        } catch (Exception exception) {
            priceField.setText("请输入正确格式！");
            return;
        }
        if(price < 0) {
            priceField.setText("请输入大于等于0的数！");
            return;
        }

        double num = 0.0;
        try{
            num = Double.parseDouble(numField.getText());
        } catch (Exception exception) {
            numField.setText("请输入正确格式！");
            return;
        }
        if(num < 0) {
            numField.setText("请输入大于等于0的数！");
            return;
        }


        double totalPrices = 0.0;
        double sum = price * num;
        totalPrices = cashContext.getResult(sum);
        total = total + totalPrices;


        String message = "单价： " + price + " 数量： " + num + type + " 合计： " + formatParsing(total)  + "\n";
        if(cashContext.getCs() instanceof CashVIP) {
            int totalCredit = ((CashVIP) cashContext.getCs()).getCredit(sum);
            totCredit += totalCredit;
            message = message + "，累计积分：" + totalCredit;
        }

        listArea.append(message);
        resField.setText(formatParsing(total));

    }

    public void reset() {
        total = 0.0;
        listArea.setText("");
        resField.setText(Double.toString(total));
        priceField.setText("0");
        numField.setText("0");
        totCredit = 0;
    }


}
