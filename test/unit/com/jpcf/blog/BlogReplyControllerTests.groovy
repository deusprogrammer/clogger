package com.jpcf.blog



import org.junit.*
import grails.test.mixin.*

@TestFor(BlogReplyController)
@Mock(BlogReply)
class BlogReplyControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/blogReply/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.blogReplyInstanceList.size() == 0
        assert model.blogReplyInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.blogReplyInstance != null
    }

    void testSave() {
        controller.save()

        assert model.blogReplyInstance != null
        assert view == '/blogReply/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/blogReply/show/1'
        assert controller.flash.message != null
        assert BlogReply.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/blogReply/list'

        populateValidParams(params)
        def blogReply = new BlogReply(params)

        assert blogReply.save() != null

        params.id = blogReply.id

        def model = controller.show()

        assert model.blogReplyInstance == blogReply
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/blogReply/list'

        populateValidParams(params)
        def blogReply = new BlogReply(params)

        assert blogReply.save() != null

        params.id = blogReply.id

        def model = controller.edit()

        assert model.blogReplyInstance == blogReply
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/blogReply/list'

        response.reset()

        populateValidParams(params)
        def blogReply = new BlogReply(params)

        assert blogReply.save() != null

        // test invalid parameters in update
        params.id = blogReply.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/blogReply/edit"
        assert model.blogReplyInstance != null

        blogReply.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/blogReply/show/$blogReply.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        blogReply.clearErrors()

        populateValidParams(params)
        params.id = blogReply.id
        params.version = -1
        controller.update()

        assert view == "/blogReply/edit"
        assert model.blogReplyInstance != null
        assert model.blogReplyInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/blogReply/list'

        response.reset()

        populateValidParams(params)
        def blogReply = new BlogReply(params)

        assert blogReply.save() != null
        assert BlogReply.count() == 1

        params.id = blogReply.id

        controller.delete()

        assert BlogReply.count() == 0
        assert BlogReply.get(blogReply.id) == null
        assert response.redirectedUrl == '/blogReply/list'
    }
}
