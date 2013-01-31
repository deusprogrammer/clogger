package com.jpcf.blog



import org.junit.*
import grails.test.mixin.*

@TestFor(AvatarController)
@Mock(Avatar)
class AvatarControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/avatar/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.avatarInstanceList.size() == 0
        assert model.avatarInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.avatarInstance != null
    }

    void testSave() {
        controller.save()

        assert model.avatarInstance != null
        assert view == '/avatar/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/avatar/show/1'
        assert controller.flash.message != null
        assert Avatar.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/avatar/list'

        populateValidParams(params)
        def avatar = new Avatar(params)

        assert avatar.save() != null

        params.id = avatar.id

        def model = controller.show()

        assert model.avatarInstance == avatar
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/avatar/list'

        populateValidParams(params)
        def avatar = new Avatar(params)

        assert avatar.save() != null

        params.id = avatar.id

        def model = controller.edit()

        assert model.avatarInstance == avatar
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/avatar/list'

        response.reset()

        populateValidParams(params)
        def avatar = new Avatar(params)

        assert avatar.save() != null

        // test invalid parameters in update
        params.id = avatar.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/avatar/edit"
        assert model.avatarInstance != null

        avatar.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/avatar/show/$avatar.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        avatar.clearErrors()

        populateValidParams(params)
        params.id = avatar.id
        params.version = -1
        controller.update()

        assert view == "/avatar/edit"
        assert model.avatarInstance != null
        assert model.avatarInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/avatar/list'

        response.reset()

        populateValidParams(params)
        def avatar = new Avatar(params)

        assert avatar.save() != null
        assert Avatar.count() == 1

        params.id = avatar.id

        controller.delete()

        assert Avatar.count() == 0
        assert Avatar.get(avatar.id) == null
        assert response.redirectedUrl == '/avatar/list'
    }
}
