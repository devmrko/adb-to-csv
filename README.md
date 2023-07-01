# write CVS file from Oracle ADB table select result

## Configuration
- Oracle Database wallet is necessary
- when run the application, some of libraries are needed to be declare like below
```
-  java –classpath
      ./lib/ojdbc8.jar:./lib/ucp.jar:./lib/oraclepki.jar:./lib/osdt_core.jar:./lib/osdt_cert.jar:. -jar "runnable jar file"
```  
- when you use eclipse or other IDE tools, need to add ojdbc.jar and other essential jar libraries in the referenced libraries path
- in application.properties file, you need to fill up profile, wallet path, user, password, and file path

## Work flow
- call RESTful API, then select table from ADB, and write CSV file as result

## reference
- JDBC Thin Connections with a Wallet (mTLS): https://docs.oracle.com/en/cloud/paas/autonomous-database/adbsa/connect-jdbc-thin-wallet.html#GUID-20656D84-4D79-4EE9-B55F-333053948966
