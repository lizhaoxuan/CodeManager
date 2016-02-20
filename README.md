# CodeManager
#个人代码片段库


###Package:date
**日期类**相关代码片段

=============

*[DateConvert.java](/CodeManager/app/src/main/java/com/lizhaoxuan/codemanager/date/DateConvert.java)* 日期计算转换

	/**
     * 是否循环状态下，计算当前日期与给定日期之间的天数
     */
    private long calculateDate(Date nowDate, Date targetDate, boolean loop)
    
    /**
     * 字符串转Date
     */
    public Date toDate(String date)
    
    /**
     * 拿到Date 年月日
     */
    public void getYearMonthDay(Date date)



###Package:function
**功能类**相关代码片段

==================
*[BitmapCompress.java](/CodeManager/app/src/main/java/com/lizhaoxuan/codemanager/function/BitmapCompress.java)* 图片压缩

	/**
     * 将图片以等比例压缩。通常用于从文件读取图片
     * @param filename   图片路径名
     * @param sampleSize  压缩为1/sampleSize
     * @return
     */
    public static Bitmap doBitmapRatio(String filename,int sampleSize)
    
    public static Bitmap doBitmapRatio(String filename,int srcWidth,int dstWidth)
    
    /**
     * 生成缩略图，只适合较小的图片进行缩略图生成
     */
    public static Bitmap createThumbBitmap(Bitmap bm,int squareW,int squareH)
    
    
*[InputMethod.java](/CodeManager/app/src/main/java/com/lizhaoxuan/codemanager/function/InputMethod.java)* 输入法软键盘相关

	/**
     * 软键盘的弹出和隐藏监听方法。（原理：通过监听布局变化判断软键盘是否弹出）
     * 通过监听键盘弹出和隐藏 从而改变布局效果
     * 纯View内部逻辑改变，且需求随时可能变化，不建议放入model层
     */
    public void inputMethodEvent(final View view)
    
    /**
     * 动态隐藏软键盘
     */
    public static void hideSoftInput(Activity activity)
    
    public static void hideSoftInput(Context context, EditText edit)
    
    /**
     * 动态显示软键盘
     */
    public static void showSoftInput(Context context, EditText edit)
    
    /**
     * 动态显示或者是隐藏软键盘
     */
    public static void toggleSoftInput(Context context, EditText edit)


###Package:screen
**屏幕类**相关代码片段

=============

*[ScreenParameter.java](/CodeManager/app/src/main/java/com/lizhaoxuan/codemanager/screen/ScreenParameter.java)* 屏幕参数计算

	//屏幕宽度
    int screenWidth;
    //屏幕高度
    int screenHeight;
    //状态栏高度
    int stateHeight;
    //标题栏高度
    int titleHeight;
	public void screenParameter(Activity activity)


###Package:sort
**算法类**相关代码片段

*整理自白话算法*

*[Swap.java](/CodeManager/app/src/main/java/com/lizhaoxuan/codemanager/sort/Swap.java)* 两个数交换的三种方式
	
*[BubbleSort.java](/CodeManager/app/src/main/java/com/lizhaoxuan/codemanager/sort/BubbleSort.java)* 冒泡排序的几种实现
	
*[InsertSort.java](/CodeManager/app/src/main/java/com/lizhaoxuan/codemanager/sort/InsertSort.java)* 直接插入排序的几种实现
	
*[SelectSort.java](/CodeManager/app/src/main/java/com/lizhaoxuan/codemanager/sort/SelectSort.java)* 直接选择排序
	
*[ShellSort.java](/CodeManager/app/src/main/java/com/lizhaoxuan/codemanager/sort/ShellSort.java)* 希尔排序
	
*[QuickSort.java](/CodeManager/app/src/main/java/com/lizhaoxuan/codemanager/sort/QuickSort.java)* 快速排序
	
*[MergeSort.java](/CodeManager/app/src/main/java/com/lizhaoxuan/codemanager/sort/MergeSort.java)* 归并排序
	
*[HeapSort.java](/CodeManager/app/src/main/java/com/lizhaoxuan/codemanager/sort/HeapSort.java)* 堆排序

###Package:string
**字符串**相关代码片段

=============

*[CamelCaseUtils.java](/CodeManager/app/src/main/java/com/lizhaoxuan/codemanager/string/CamelCaseUtils.java)* 驼峰式与下划线式命名转换

	/**
     * 转为下划线字符串
     */
    public static String toUnderlineName(String s)
    
    /**
     * 转为驼峰式字符串
     */
    public static String toCamelCase(String s)
    
    /**
     * 首字母大写的驼峰式
     */
    public static String toCapitalizeCamelCase(String s)

