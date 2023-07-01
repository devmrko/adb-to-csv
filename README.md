# Write a CVS file from the Oracle ADB table's select result

## Configuration
- Oracle Database wallet is necessary
- When running the application, some of the libraries are needed to be declared like below
```
-  java –classpath
      ./lib/ojdbc8.jar:./lib/ucp.jar:./lib/oraclepki.jar:./lib/osdt_core.jar:./lib/osdt_cert.jar:. -jar "runnable jar file"
```  
- When you use Eclipse or other IDE tools, you need to add ojdbc.jar and other essential jar libraries in the referenced libraries path
- In the application.properties file, you need to fill up the profile, wallet path, user, password, and file path

## Workflow
- Call RESTful API, then select a table from ADB, and write CSV file as a result

## Reference
- JDBC Thin Connections with a Wallet (mTLS): https://docs.oracle.com/en/cloud/paas/autonomous-database/adbsa/connect-jdbc-thin-wallet.html#GUID-20656D84-4D79-4EE9-B55F-333053948966
