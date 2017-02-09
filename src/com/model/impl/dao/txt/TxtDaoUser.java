package com.model.impl.dao.txt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.model.dao.DaoUser;
import com.model.dto.TransferUser;
import com.model.exceptions.FileErrorException;
import com.model.exceptions.UserNotFoundException;
import com.model.impl.dto.TransferUserImp;
import com.util.Common;
import com.util.Tools;
import com.util.Tools.UserPermission;

public class TxtDaoUser implements DaoUser {
	
	private static TxtDaoUser factory;
	
	public static TxtDaoUser getInstance() {
		if(factory == null)
			factory = new TxtDaoUser();
		
		return factory;
	}
	
	private TxtDaoUser() {
		
	}
	
	@Override
	public ArrayList<String> readLoggedUser() {
		
		BufferedReader bf = null;
        Path input = Paths.get(Common.LOGGEDUSERS_FILE);

        ArrayList<String> userList = new ArrayList<String>();

        String username;

        try {
            bf = Files.newBufferedReader(input, Charset.defaultCharset());
            String line = bf.readLine();

            while(line != null) {

                username = (!line.isEmpty()) ? line.trim() : null;
                userList.add(username);

                line = bf.readLine();
            }
            
            Tools.NUMLOGGEDUSERS = userList.size();
                     

        } catch (IOException ioe) {
            System.exit(2);
		} finally {
            try {
                bf.close();
            } catch (IOException ioe) {
                System.exit(1);
            }
        }

        return userList;     
	}
	
	@Override
	public void writeLoggedUser(ArrayList<String> userList) {
		Path writeFilePath = Paths.get(Common.LOGGEDUSERS_FILE);
        FileWriter writer = null;
        String newLine = System.getProperty("line.separator");

        if(Files.exists(writeFilePath)) {
            try {

                writer = new FileWriter(writeFilePath.toString());

                if(userList == null || userList.size() == 0) {
                    writer.write("");
                    return;
                }

                for(String u: userList) {
                    if(u != null)
                        writer.write(u + newLine);
                    else
                        writer.write(newLine);
                }

            } catch (IOException e1) {
                System.exit(2);

            } finally {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.exit(2);
                }
            }
        }

        else {
            try {

                File file = new File(Common.LOGGEDUSERS_FILE);
                writer = new FileWriter(file.toString());

                for(String u: userList) {
                    if(u != null)
                        writer.write(u + newLine);
                    else
                        writer.write(newLine);
                }

            } catch (IOException e) {
                System.exit(2);

            } finally {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.exit(2);
                }
            }
        }
		    
	}
	
	@Override
	public void login(TransferUser u) {
		// TODO Auto-generated method stub
        
        ArrayList<String> loggedUserList = readLoggedUser();
        loggedUserList.add(u.getUserName());
        
        Tools.NUMLOGGEDUSERS++;
        writeLoggedUser(loggedUserList);
		
	}

	@Override
	public void logout(TransferUser u) {
		// TODO Auto-generated method stub
        
        ArrayList<String> loggedUserList = readLoggedUser();
        loggedUserList.remove(u.getUserName());
        
        Tools.NUMLOGGEDUSERS--;
        writeLoggedUser(loggedUserList);
	
	}
	
	@Override
	public ArrayList<TransferUser> readUsers() {
		// TODO Auto-generated method stub
		BufferedReader bf = null;
        Path input = Paths.get(Common.STAFF_FILE);
        
        ArrayList<TransferUser> userList = new ArrayList<TransferUser>();
        
        String employeeName, userName, password;
        int permission;
        UserPermission userPermission = UserPermission.NONE;


        try {
            bf = Files.newBufferedReader(input, Charset.defaultCharset());
            String line = bf.readLine();

            if(line != null && Tools.isInteger(line)) // implement isNumber from FP
                Tools.NUMUSERS = Integer.parseInt(line.trim());

            bf.readLine(); // white line
            line = bf.readLine();

            while(line != null) {

                permission = (Tools.isInteger(line.trim())) ? Integer.parseInt(line.trim()) : -1;
                line = bf.readLine();

                employeeName =  (!line.isEmpty()) ? line.trim() : null;
                line = bf.readLine();

                userName = (!line.isEmpty()) ? line.trim() : null;
                line = bf.readLine();

                password = (!line.isEmpty()) ? line.trim() : null;

                if(permission == 1)
                    userPermission = UserPermission.ADMIN;
                else if (permission == 2)
                    userPermission = UserPermission.MANAGER;
                else if(permission == 3)
                    userPermission = UserPermission.VENDOR;
                else
                	userPermission = UserPermission.NONE;

                TransferUser user = new TransferUserImp(userPermission, employeeName, userName, password);
                userList.add(user);

                bf.readLine(); // white line
                line = bf.readLine();
            }

        

	        if(userList.size() != Tools.NUMUSERS)
	            throw new FileErrorException("El n�mero de empleados indicado no es correcto.");
		} catch (IOException ioe) {
		            System.exit(2);
		} catch (FileErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		            try {
		                bf.close();
		            } catch (IOException ioe) {
		                System.exit(1);
		            }
		        }   
        return userList;
	}

	@Override
	public void writeUsers(ArrayList<TransferUser> userList) {
		// TODO Auto-generated method stub
		Path writeFilePath = Paths.get(Common.STAFF_FILE);
        FileWriter writer = null;
        String newLine = System.getProperty("line.separator");

        if(Files.exists(writeFilePath)) {
            try {

                writer = new FileWriter(writeFilePath.toString());

                if(userList == null || userList.size() == 0) {
                    writer.write("0");
                    return;
                }

                writer.write(userList.size() + newLine + newLine);
                
                for(TransferUser u: userList)
                	writer.write(u.toFile());

            } catch (IOException e) {
                System.exit(2);

            } finally {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.exit(2);
                }
            }
        }

        else {
            try {

                File file = new File(Common.STAFF_FILE);
                writer = new FileWriter(file.toString());

                if(userList == null || userList.size() == 0) {
                    writer.write("0");
                    return;
                }

                writer.write(userList.size() + newLine + newLine);
                
                for(TransferUser u: userList)
                	writer.write(u.toFile());

            } catch (IOException e) {
                System.exit(2);

            } finally {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.exit(2);
                }
            }
        }
	}

	@Override
	public void addUser(TransferUser u) {
		// TODO Auto-generated method stub
		ArrayList<TransferUser> userList = readUsers();
		userList.add(u);
		
		Tools.NUMUSERS++;
		writeUsers(userList);
		
	}

	@Override
	public void removeUser(TransferUser u) {
		// TODO Auto-generated method stub
		ArrayList<TransferUser> userList = readUsers();
		int pos = userList.indexOf(searchUser(userList, u));
		userList.remove(pos);
		
		Tools.NUMUSERS--;
		writeUsers(userList);
		
	}

	@Override
	public void modifyUser(TransferUser u) throws UserNotFoundException {
		// TODO Auto-generated method stub
		ArrayList<TransferUser> userList = readUsers();
		TransferUser user = searchUser(userList, u);
        if(u == null)
            throw new UserNotFoundException(user.getUserName() + " no se encuentra en el staff.");
        
        user.setUserName(u.getUserName());
        user.setPassword(u.getPassword());
        user.setPermission(u.getPermission());
        
        writeUsers(userList);
        
	}
	
	@Override
	public boolean validateUser(String username, String password) {
		// TODO Auto-generated method stub
		ArrayList<TransferUser> userList = readUsers();
		TransferUser u = new TransferUserImp();
		u.setUserName(username);
		u.setPassword(password);
		TransferUser users = searchUser(userList, u);

        if(users != null && users.checkPass(password))
            return true;

        return false;
	}
	
	@Override
	public TransferUser searchUser(ArrayList<TransferUser> userList, TransferUser u) {
		// TODO Auto-generated method stub
		for(TransferUser us : userList) {
            if(us.getUserName().equalsIgnoreCase(u.getUserName())) {
                return us;
            }
		}
        return null;
	}
	
	@Override
	public boolean searchUser(ArrayList<String> userList, String u) {
		// TODO Auto-generated method stub
		for(String us : userList) {
            if(us.equalsIgnoreCase(u)) {
                return true;
            }
		}
        return false;
	}

}