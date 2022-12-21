```plantuml
node "<<Amazon EC2>>" as ec2 {
	node "<<instance>>\ninstance = m6in4xlarge" {
		node "<<spring>>\nversion = 5" as spring {
    		artifact "Eirene.jar" as jar
    		component "Eirene" as eirene
    		jar ..> eirene : <<manifest>>
    		component SpringMVC as mvc
    		eirene -(0- mvc
    		component JPA
    		eirene -(0- JPA
    		artifact "pgJDBC.jar\nJDBCVersion=4.2" as jdbc
    		jdbc ..> JPA : <<manifest>>
		}
	}
}

node "<<Amazon RDS for Postgres>>" as rds {
    component "PostgresDB" as db
}

node "<<client>>" as client {
    node "<<gradle>>\nversion = 7.4" as gradle {
        component "DataSender" as sender
        artifact "DataSender.jar" as senderJar
        senderJar ..> sender : <<manifest>>
    }
    node "<<browser>>" as browser
}

node "<<MindFlex>>" as mindflex

node "<<Arduino>>" as arduino

component "<<Light Sensor>>" as light
component "<<Humidity Sensor>>" as humidity
component "<<Heart Rate Sensor>>" as hr
component "<<Temperature Sensor>>" as temp
component "<<Noise Sensor>>" as sound

jdbc "1" -- "1" db
mvc "1" -- "*" sender : HTTPS/REST
mvc "1" -- "*" browser : HTTPS/HTML
'sender -- mindflex : Bluetooth
sender "1" -- "1" mindflex : SerialConnection
'sender -- arduino : USB
sender "1" -- "1" arduino : SerialConnection

arduino -(0- light
arduino -(0- humidity
arduino -(0- hr
arduino -(0- temp
arduino -(0- sound
```