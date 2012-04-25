package net.continuumsecurity.ropeytasks

class User {
	String username
	String password
	String email
	String firstname
	String lastname
	int role = 0
	int failedLogins = 0
	
	static hasMany = [tasks: Task]
	
    static constraints = {
    }
}
