"""fortiadc_lab_server URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/1.11/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  url(r'^$', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  url(r'^$', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.conf.urls import url, include
    2. Add a URL to urlpatterns:  url(r'^blog/', include('blog.urls'))
"""
from django.conf.urls import url
from django.conf.urls import include
from django.contrib import admin
from django.views.generic import TemplateView


from rest_framework import routers
from app_topo import views


router = routers.DefaultRouter()
router.register(r'userdata/uslabtopologydata', views.us_lab_topology_data_viewset)
router.register(r'userdata/uslabsettingsdata', views.us_lab_settings_viewset)
router.register(r'userdata/uslabresourceplandata', views.us_lab_resource_plan_viewset)


# us_lab_topology_data_view = views.us_lab_topology_data_viewset.as_view({'get': 'list', 'post':'create', 'put':'update'})


urlpatterns = [
    url(r'^admin/', admin.site.urls),
    url(r'^$', TemplateView.as_view(template_name="index.html")),
    url(r'^', include(router.urls)),
    url(r'^api-auth/', include('rest_framework.urls', namespace='rest_framework')),
]
