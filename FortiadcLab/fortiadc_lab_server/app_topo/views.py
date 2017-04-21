# -*- coding: utf-8 -*-
from __future__ import unicode_literals


from rest_framework.pagination import PageNumberPagination

from models import us_lab_topology_data
from models import us_lab_settings_data
from models import us_lab_resource_plan_data


import django_filters.rest_framework
from rest_framework import viewsets, filters
from app_topo.serializers import us_lab_topology_data_serializer
from app_topo.serializers import us_lab_settings_serializer
from app_topo.serializers import us_lab_resource_plan_serializer



# Create your views here.



class StandardResultsSetPagination(PageNumberPagination):
    page_size = 100
    page_size_query_param = 'page_size'
    max_page_size = 1000



class us_lab_topology_data_viewset(viewsets.ModelViewSet):
    queryset = us_lab_topology_data.objects.all()
    serializer_class = us_lab_topology_data_serializer

class us_lab_settings_viewset(viewsets.ModelViewSet):
    queryset = us_lab_settings_data.objects.all()
    serializer_class = us_lab_settings_serializer

class us_lab_resource_plan_viewset(viewsets.ModelViewSet):
    queryset = us_lab_resource_plan_data.objects.all()
    serializer_class = us_lab_resource_plan_serializer
    pagination_class = StandardResultsSetPagination
    filter_backends = (django_filters.rest_framework.DjangoFilterBackend,)
    filter_fields = ("name",)

