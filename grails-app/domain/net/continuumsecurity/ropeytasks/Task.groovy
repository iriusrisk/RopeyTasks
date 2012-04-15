package net.continuumsecurity.ropeytasks

class Task {
	String name
	String detail
	Date dueDate
	int status
	
	static belongsTo = [user: User]
	
    static constraints = {
		detail(nullable: true)
    }
}
