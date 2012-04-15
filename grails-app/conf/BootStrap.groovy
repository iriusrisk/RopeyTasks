import net.continuumsecurity.ropeytasks.*

class BootStrap {
    def init = { servletContext ->
		loadData();
    }
	
    def destroy = {
    }
	
	def loadData() {
		def bob = new User(username:"bob",password:"password",firstname:"Robert",lastname:"McBride",email:"bob@continuumsecurity.net").save()
		def alice = new User(username:"alice",password:"password",firstname:"Alice",lastname:"O'Reilly",email:"alice@continuumsecurity.net").save()
		def admin = new User(username:"admin",password:"password",firstname:"Administrator",lastname:"Reynolds",role:1,email:"admin@continuumsecurity.net").save()
		
		def bobTask = new Task(name:"Bob's shopping",detail:"Eggs, Milk and Cheese baby, yeah.", status:0,dueDate: new Date(),user: bob).save()
		def aliceTask = new Task(name:"Alice's shopping",detail:"My stuff", status:0,dueDate: new Date(),user: alice).save()
	}
}
