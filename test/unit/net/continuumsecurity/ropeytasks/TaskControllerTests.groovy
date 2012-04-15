package net.continuumsecurity.ropeytasks



import org.junit.*
import grails.test.mixin.*

@TestFor(TaskController)
@Mock(Task)
class TaskControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/task/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.taskInstanceList.size() == 0
        assert model.taskInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.taskInstance != null
    }

    void testSave() {
        controller.save()

        assert model.taskInstance != null
        assert view == '/task/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/task/show/1'
        assert controller.flash.message != null
        assert Task.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/task/list'


        populateValidParams(params)
        def task = new Task(params)

        assert task.save() != null

        params.id = task.id

        def model = controller.show()

        assert model.taskInstance == task
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/task/list'


        populateValidParams(params)
        def task = new Task(params)

        assert task.save() != null

        params.id = task.id

        def model = controller.edit()

        assert model.taskInstance == task
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/task/list'

        response.reset()


        populateValidParams(params)
        def task = new Task(params)

        assert task.save() != null

        // test invalid parameters in update
        params.id = task.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/task/edit"
        assert model.taskInstance != null

        task.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/task/show/$task.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        task.clearErrors()

        populateValidParams(params)
        params.id = task.id
        params.version = -1
        controller.update()

        assert view == "/task/edit"
        assert model.taskInstance != null
        assert model.taskInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/task/list'

        response.reset()

        populateValidParams(params)
        def task = new Task(params)

        assert task.save() != null
        assert Task.count() == 1

        params.id = task.id

        controller.delete()

        assert Task.count() == 0
        assert Task.get(task.id) == null
        assert response.redirectedUrl == '/task/list'
    }
}
