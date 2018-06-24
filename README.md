羽毛球场管理系统
===
任务（未完待续）
---
类
---
### Util-工具类
### 方法
        /**
         * 枚举类型
         */
        enum DayType{
            WEEKDAY,WEEKEND
        }

        /**
         * 将日期转为周几，并且返回枚举类型
         * 枚举类型有周内和周末
         */
         public static DayType dateToWeek(String date);

         /** 
         * 字符串转时间格式，返回时间的小时
         */
        public static int stringToTime(String time);
	
### Encoder-编码器
### 属性
    //命令
    private String command;  
    //用户
    private String user;
    //日期
    private String date;
    //时间段
    private String time;
    //预定或取消场馆
    private String site;
    //行为：预定、取消
    private String action;
    //提示信息
    private String msg="";

### 方法
    /**
     * 构造函数传入命令，并进入encode方法
     * @param command
     */
    public Encoder(String command);

    /**
     * 对命令进行解析，判断格式是否正确
     * @return 
     */
    public void encode();

    /**
     * 对命令进行格式化，获取user、date、time、action信息，方便后续处理
     * @param str
     */
    public void format(String[] str);

### Site-场馆
### 属性
    //场馆名
    private String name; 
    //key记录日期，value时一个长度为13的String数组，记录各个时间段的userID，没被预定则为""
    private Map<String,String[]> date_time=new HashMap<String,String[]>();
    //提示信息
    private String msg;

### 方法
    /**
    * 判断场馆在制定日期的指定时间段内是否可用
    */
    public boolean isAvailable(String date,int start,int end);

    /**
    * 预定
    * 先判断是否可用，不可用设置msg并且返回
    * 可用就将遇到信息加入到date_time中，返回成功信息
    */
    public void book(String user,String date,String time);

    /**
    * 取消
    * 先判断场地是否可用，如果可用，说明要取消的订单不存在，返回
    * 如果不可用，并且取消与预定的信息一致，则返回成功信息
    */
    public void cancle(String user,String date,String time);
    
### Fee-计费模块
### 属性
    //周内各时段价格
    private static int[] weekday_fee={30,30,30,50,50,50,50,50,50,80,80,60,60};
    //周末各时段价格
    private static int[] weekend_fee={40,40,40,50,50,50,50,50,50,60,60,60,60};
    //预定或取消产生的费用
    private int fee;
    //折扣
    private double discount=1.0;
    //优惠金额
    private int discountFee=0;
    //从文本资源中读取的优惠信息
    private List<Map<String,String>> discountInfo=new ArrayList<Map<String,String>>();
    
### 方法
    /**
     * 构造方法，自动获取优惠信息
     */
    public Fee();

    /**
     * 发生预定请求时计算金额
     */
    public int payment(String date,String time);

    /**
     * 发生取消请求时计算违约金
     */
    public int penalty(String date,String time);

    /**
     * 从资源文件中读取优惠信息
     */
    public void getDiscountInfo();

    /**
     * 计算优惠信息
     */
    public void discountCalculate(String date);
    
### Order-订单类
### 属性
    //用一个存储订单信息的集合，将每一条订单记录拼接成一个字符串
    private List<String> orderList=new ArrayList<String>();
    //该订单所属的场地
    private String siteName;
    //收入总和
    private int total;
### 方法
    /**
     * 构造方法，传入场地名
     */
    public Order(String siteName);

    /**
     * 生成订单String，并加入orderList中
     * 生成的订单记录可能是预定，也可能是取消
     */
    public void generateOrder(String date,String time,String action);

    /**
     * 打印本场地的订单列表及收入信息汇总
     */
    public void printSummary();
流程
---
### 通过App类启动
    1、初始化四个场馆和四个订单
    2、输入命令，并自动进入编码器判断语法是否符合
    3、语法没有问题，就判断请求是预定还是取消还是打印汇入汇总
    4、如果是预定，就对对应的场馆进行预定操作，如果预定成功就创建一条订单记录（这个过程中会调用Fee类计算预定金额、是否有优惠价格及小计）
    5、如果是取消，就对对应的场馆进行取消操作，如果取消成功就创建一条订单记录（这个过程中会调用Fee类计算违约金及小计）
    6、如果是打印，则打印所有场馆的订单信息及小计，并计算总计
效果图
---
