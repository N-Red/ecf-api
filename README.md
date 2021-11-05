DataBase Name => ecf-back


http://localhost:8080/createPatient

{
"lastName" : "Red",
"firstName" : "Nico",
"birthdate" : "2000-01-01",
"address" : "Ville de france",
"gender" : "Homme"
}

http://localhost:8080/readPatient=1

http://localhost:8080/updatePatient
{
"id" : 1,
"lastName" : "Red updated",
"firstName" : "Nico updated",
"birthdate" : "2000-01-01",
"address" : "Ville de france updated",
"gender" : "Homme"
}

http://localhost:8080/deletePatient=1

http://localhost:8080/allPatients

http://localhost:8080/ageOfPatient=1

http://localhost:8080/patientsWithSameName=Red