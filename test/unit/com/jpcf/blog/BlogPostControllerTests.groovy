package com.jpcf.blog



import org.junit.*
import grails.test.mixin.*

@TestFor(BlogPostController)
@Mock(BlogPost)
class BlogPostControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/blogPost/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.blogPostInstanceList.size() == 0
        assert model.blogPostInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.blogPostInstance != null
    }

    void testSave() {
        controller.save()

        assert model.blogPostInstance != null
        assert view == '/blogPost/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/blogPost/show/1'
        assert controller.flash.message != null
        assert BlogPost.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/blogPost/list'

        populateValidParams(params)
        def blogPost = new BlogPost(params)

        assert blogPost.save() != null

        params.id = blogPost.id

        def model = controller.show()

        assert model.blogPostInstance == blogPost
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/blogPost/list'

        populateValidParams(params)
        def blogPost = new BlogPost(params)

        assert blogPost.save() != null

        params.id = blogPost.id

        def model = controller.edit()

        assert model.blogPostInstance == blogPost
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/blogPost/list'

        response.reset()

        populateValidParams(params)
        def blogPost = new BlogPost(params)

        assert blogPost.save() != null

        // test invalid parameters in update
        params.id = blogPost.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/blogPost/edit"
        assert model.blogPostInstance != null

        blogPost.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/blogPost/show/$blogPost.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        blogPost.clearErrors()

        populateValidParams(params)
        params.id = blogPost.id
        params.version = -1
        controller.update()

        assert view == "/blogPost/edit"
        assert model.blogPostInstance != null
        assert model.blogPostInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/blogPost/list'

        response.reset()

        populateValidParams(params)
        def blogPost = new BlogPost(params)

        assert blogPost.save() != null
        assert BlogPost.count() == 1

        params.id = blogPost.id

        controller.delete()

        assert BlogPost.count() == 0
        assert BlogPost.get(blogPost.id) == null
        assert response.redirectedUrl == '/blogPost/list'
    }
}
