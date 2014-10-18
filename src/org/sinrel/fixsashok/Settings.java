package org.sinrel.fixsashok;

public class Settings {
	
	//Адреса ссылок
	public static final String firstLink = "http://rubukkit.org/"; 
	public static final String secondLink = "";
	public static final String thirdLink = "";
	
	public static final String newsLink = ""; //Адрес страницы новостей. Пример: http://google.com/
	public static final boolean playMusic = true;
	public static final double musicVolume = 0.2; //Громкость звука. (от 0 до 1)
	
	public static final String masterVersion     = "2.0"; //Версия лаунчера
	public static final byte versionCode = 2;//Код версии
	
	
	/** Настройка заголовка лаунчера */
	public static final String  title		         = "Launcher"; //Заголовок лаунчера
	public static final String  titleInGame  	     = "Minecraft"; //Заголовок лаунчера после авторизации
	public static final String  baseconf		     = "voxelaria"; //Папка с файлом конфигурации
	public static final String  pathconst		     = "voxelaria/%SERVERNAME%"; //Конструктор пути к папке с MC
	/** Параметры подключения */
	public static final String  domain	 	         = "zenit.ssh22.net";//Домен сайта
	public static final String  siteDir		         = "site";//Папка с файлами лаунчера на сайте
	public static final String  updateFile		     = "http://zenit.ssh22.net/site/launcher/test";//Ссылка на обновления лаунчера. Не писать на конце ".exe .jar"!
	public static final String  buyVauncherLink      = "http://plati.ru/"; //Ссылка на страницу покупки ваучеров
	public static final String[] test = {"wireshark", "cheat"};  //Список запрещенных процессов.
	
	public static int height                         = 532;      //Высота окна клиента
	public static int width                          = 900;      //Ширина окна клиента

	/** Настройки структуры лаунчера */
	public static boolean useAutoenter	         =  false;  //Использовать функцию автозахода на выбранный сервер
	public static boolean useRegister		     =  true;   //Использовать Регистрацию в лаунчере
	public static boolean useMulticlient		 =  true;   //Использовать функцию "по клиенту на сервер"
	public static boolean useStandartWB		     =  true;   //Использовать стандартный браузер для открытия ссылок
	public static boolean usePersonal		     =  true;   //Использовать Личный кабинет
	public static boolean customframe 		     =  true;   //Использовать кастомный фрейм
	public static boolean useConsoleHider		 =  false;  //Использовать скрытие консоли клиента
	public static boolean useModCheckerTimer	 =  true;   //Перепроверка jar через 30 секунд
	public static int     useModCheckerint       =  2;      //Количество раз перепроверки jar во время игры
	public static boolean assetsfolder           =  false;  //Скачивать assets из папки, или из архива (true=из папки false=из архива) в connect.php должно быть так же.

	public static final String protectionKey	 = "1234567890"; //Ключ защиты сессии. Никому его не говорите.
	public static final String key1              = "1234567891234567"; //16 Character Key Ключ пост запросов
	public static final String key2              = "1234567891234567"; //16 Character Key Ключ пост запросов

	public static boolean debug		 	         =  true; //Отображать все действия лаунчера (отладка)(true/false)
	
	public static String[] servers =
	{
		"Offline, localhost, 25565, 1.5.2",
	};
	
}
