	AppCompat，库，可兼容到api7，android2.1以上
	自带三种主题
	Theme.AppCompat:黑色主题
	Theme.Appcompat.Light:浅色主题
	Theme.Appcompat.Light.DarkActionBar:带黑色工具栏的浅色主题
	在AM中设置主题格式
	AppTheme库可以统一各系统版本的主题风格，从而省去了适配的麻烦。所以可以删除多余版本的styles文件
	使用AppTheme库必须是AppCompatActivity子类，该类恰恰就是FragmentActivity的子类。
	
	菜单
	showAsAction属性用于指定菜单选项是否显示在工具栏，若溢出可三个点图标访问
	
	隐式intent
	1、要执行的操作
	通常以intent类中的常量表示，如要访问查看某个url，可以使用Intent.ACTION_VIEW，发送邮件Intent.ACTION_SEND
	2、要访问数据的位置
	如某个网页的url、某个文件的uri
	3、操作涉及的数据类型
	
	4、可选类别
	
	过滤器
	action：能够胜任指定的工作
	