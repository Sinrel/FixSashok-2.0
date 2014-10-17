package net.launcher.utils;

import static net.launcher.utils.BaseUtils.buildUrl;
import static net.launcher.utils.BaseUtils.getPropertyString;
import static net.launcher.utils.BaseUtils.setProperty;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import net.y;
import net.launcher.components.Frame;
import net.launcher.components.PersonalContainer;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.sinrel.fixsashok.Settings;

public class ThreadUtils
{
	public static UpdaterThread updaterThread;
	public static Thread serverPollThread;
	static String d = y.e()+"1234";

	static String token =  new String(Frame.password.getPassword());
	
	public static void auth(final boolean personal)
	{
		BaseUtils.send("Logging in, login: " + Frame.login.getText());
		Thread t = new Thread() {
		public void run()
		{ try {
			
			boolean error = false;
			token =  new String(Frame.password.getPassword());
			if(Frame.token.equals("token"))
			{
				try {
				token =  decrypt(getPropertyString("password"), d);
				} catch (Exception e) {
					Frame.main.panel.tmpString = "Ошибка подключения";
					error = true;
				}
			}
			String answer2 = BaseUtils.execute(BaseUtils.buildUrl("launcher.php"), new Object[]
			{
				"action", encrypt("auth:"+BaseUtils.getClientName()+":"+Frame.login.getText()+":"+token+":"+GuardUtils.hash(ThreadUtils.class.getProtectionDomain().getCodeSource().getLocation().toURI().toURL())+":"+Frame.token, Settings.key2),
			});
			BaseUtils.send(answer2);
            String answer = null;
			try {
				answer = decrypt(answer2, Settings.key1);
			} catch (Exception e) {}
			if(answer == null)
			{
				Frame.main.panel.tmpString = "Ошибка подключения";
				error = true;
			} else if(answer.contains("badlauncher<$>"))
			{
				Frame.main.setUpdateComp(answer.replace("badlauncher<$>_", "" ));
				return;
			} else if(answer.contains("errorLogin<$>"))
			{
				Frame.main.panel.tmpString = "Ошибка авторизации (Логин, пароль)";
				error = true;
			} else if(answer.contains("errorsql<$>"))
			{
				Frame.main.panel.tmpString = "Ошибка sql";
				error = true;
			} else if(answer.contains("client<$>"))
			{
				Frame.main.panel.tmpString = "Ошибка: "+answer.replace("client<$>", "клиент")+" не найден";
				error = true;
			} else if(answer.contains("temp<$>"))
			{
				Frame.main.panel.tmpString = "Подождите, перед следущей попыткой ввода (Логин Пароль)";
				error = true;	
			} else if(answer.contains("badhash<$>"))
			{
				Frame.main.panel.tmpString = "Ошибка: Неподдерживаемый способ хеширования";
				error = true;	
			} else if(answer.split("<br>").length != 4)
			{
				Frame.main.panel.tmpString = answer;
				error = true;
			} if(error)
			{
				Frame.main.panel.tmpColor = Color.red;
				try
				{
					sleep(2000);
				} catch (InterruptedException e) {}
				Frame.main.setAuthComp();
			} else
			{
				String version = answer.split("<br>")[0].split("<:>")[0];
				
			if(!version.equals(Settings.masterVersion))
			{
				Frame.main.setUpdateComp(version);
				return;
			}
			BaseUtils.send("Logging in successful");
			
				if(personal)
				{
					Frame.main.panel.tmpString = "Загрузка данных...";
					String personal = BaseUtils.execute(BaseUtils.buildUrl("launcher.php"), new Object[]
					{
						"action", encrypt("getpersonal:0:"+Frame.login.getText()+":"+token, Settings.key2),
					});
					
                    if(personal.contains("=="))
					{
						personal = decrypt(personal, Settings.key1);
					}

					if(personal == null)
					{
						Frame.main.panel.tmpString = "Ошибка подключения";
						error = true;
					} else if(personal.contains("errorLogin"))
					{
						Frame.main.panel.tmpString = "Ошибка авторизации (Логин, пароль)";
						error = true;
					} else if(personal.contains("errorsql"))
					{
						Frame.main.panel.tmpString = "Ошибка sql";
						error = true;
					} else if(personal.contains("temp"))
					{
						Frame.main.panel.tmpString = "Подождите, перед следущей попыткой ввода (Логин Пароль)";
						error = true;
					} else if(personal.contains("noactive"))
					{
						Frame.main.panel.tmpString = "Ваш аккаунт не активирован!";
						error = true;	
					} else if(personal.contains("badhash"))
					{
						Frame.main.panel.tmpString = "Ошибка: Неподдерживаемый способ шифровки";
						error = true;	
					} else if(personal.split("<:>").length != 13 || personal.split("<:>")[0].length() != 7)
					{
						Frame.main.panel.tmpString = personal;
						error = true;
					} if(error)
					{
						Frame.main.panel.tmpColor = Color.red;
						try
						{
							sleep(2000);
						} catch (InterruptedException e) {}
						Frame.main.setAuthComp();
						return;
					} else
					{
						try {
						Frame.main.panel.tmpString = "Загрузка скина...";
						BufferedImage skinImage   = BaseUtils.getSkinImage(answer.split("<br>")[1].split("<:>")[0]);
						Frame.main.panel.tmpString = "Загрузка плаща...";
						BufferedImage cloakImage  = BaseUtils.getCloakImage(answer.split("<br>")[1].split("<:>")[0]);
						Frame.main.panel.tmpString = "Парсинг скина...";
						skinImage = ImageUtils.parseSkin(skinImage);
						Frame.main.panel.tmpString = "Парсинг плаща...";
						cloakImage= ImageUtils.parseCloak(cloakImage);
						Frame.main.panel.tmpString = BaseUtils.empty;
						PersonalContainer pc = new PersonalContainer(personal.split("<:>"), skinImage, cloakImage);
						Frame.main.setPersonal(pc);
						
						return;
						} catch(Exception e){ BaseUtils.throwException(e, Frame.main); return; }
					}
				}

				setProperty("password", encrypt(answer.split("<br>")[2].split("<br>")[0], d).replaceAll("\n|\r\n", ""));
				if(Frame.token.equals("null"))
				{
					Frame.password.setVisible(false);
					Frame.toGame.setVisible(true);
					Frame.toPersonal.setVisible(Settings.usePersonal && true);
					Frame.toAuth.setVisible(false);
					Frame.toLogout.setVisible(true);
					Frame.toRegister.setVisible(false);
					Frame.token = "token";
					Frame.login.setEditable(false);
					Frame.main.setAuthComp();
					return;
				}
				runUpdater(answer);
			} interrupt(); } catch(Exception e){ e.printStackTrace(); }
		}};
		t.setName("Auth thread");
		t.start();
	}
    
        
	public static void runUpdater(String answer)
	{
		boolean zipupdate = false;
		boolean asupdate = false;
		List<String> files = GuardUtils.updateMods(answer);
		
		String folder = BaseUtils.getMcDir().getAbsolutePath()+File.separator;
		String asfolder = BaseUtils.getAssetsDir().getAbsolutePath()+File.separator;
		if(!answer.split("<br>")[0].split("<:>")[2].split("<>")[0].equals(BaseUtils.getPropertyString(BaseUtils.getClientName() + "_zipmd5")) ||
		!new File(folder+"config").exists() || 
		Frame.main.updatepr.isSelected())
		{ 
			GuardUtils.filesize += Integer.parseInt(answer.split("<br>")[0].split("<:>")[2].split("<>")[1]);
			files.add("/"+BaseUtils.getClientName()+"/config.zip");  zipupdate = true;
		}
		
		if(!Settings.assetsfolder)
		{
			if(!answer.split("<br>")[0].split("<:>")[3].split("<>")[0].equals(BaseUtils.getPropertyString("assets_aspmd5")) ||
			!new File(asfolder+"assets").exists() ||
			Frame.main.updatepr.isSelected())
			{
				GuardUtils.filesize += Integer.parseInt(answer.split("<br>")[0].split("<:>")[3].split("<>")[1]);
				files.add("/assets.zip");  asupdate = true;
			}
		}
		
		BaseUtils.send("---- Filelist start ----");
		for(Object s : files.toArray())
		{
			BaseUtils.send("- " + (String) s);
		}
		BaseUtils.send("---- Filelist end ----");
		BaseUtils.send("Running updater...");
		updaterThread = new UpdaterThread(files, zipupdate, asupdate, answer);
		updaterThread.setName("Updater thread");
		Frame.main.setUpdateState();
		updaterThread.run();
	}
	
	public static void pollSelectedServer()
	{
		try
		{
			serverPollThread.interrupt();
			serverPollThread = null;
		} catch (Exception e) {}
		
		BaseUtils.send("Refreshing server state... (" + Frame.main.servers.getSelected() + ")");
		serverPollThread = new Thread()
		{
			public void run()
			{
				Frame.main.serverbar.updateBar("Обновление...", BaseUtils.genServerIcon(new String[]{null, "0", null}));
				int sindex = Frame.main.servers.getSelectedIndex();
				String ip = Settings.servers[sindex].split(", ")[1];
				int port = BaseUtils.parseInt(Settings.servers[sindex].split(", ")[2], 25565);
				String[] status = BaseUtils.pollServer(ip, port);
				String text = BaseUtils.genServerStatus(status);
				BufferedImage img = BaseUtils.genServerIcon(status);
				Frame.main.serverbar.updateBar(text, img);
				
				serverPollThread.interrupt();
				serverPollThread = null;
				BaseUtils.send("Refreshing server done!");
			}
		};
		serverPollThread.setName("Server poll thread");
		serverPollThread.start();
	}

	static String encrypt(String input, String key){
		  byte[] crypted = null;
		  try{
		    SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
		      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		      cipher.init(Cipher.ENCRYPT_MODE, skey);
		      crypted = cipher.doFinal(input.getBytes());
		    }catch(Exception e){
		    	System.err.println("Ключ должен быть 16 символов");
		    }
		    return new String(new sun.misc.BASE64Encoder().encode(crypted));
		}

    static String decrypt(String input, String key){
		    byte[] output = null;
		    try{
		      SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
		      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		      cipher.init(Cipher.DECRYPT_MODE, skey);
		      output = cipher.doFinal(new sun.misc.BASE64Decoder().decodeBuffer(input));
		    }catch(Exception e){
		      System.err.println("Ключ шифрование не совпадает или больше 16 символов, или полученна ошибка от launcher.php");
		      System.err.println("Проверьте настройку  в Settings.java или connect.php");
		    }
		    return new String(output);
		} 
}