# -*- coding: utf-8 -*-
from __future__ import unicode_literals


from models import us_lab_topology_data
from models import us_lab_settings_data


from rest_framework import viewsets
from app_topo.serializers import us_lab_topology_data_serializer
from app_topo.serializers import us_lab_settings_serializer



# Create your views here.


class us_lab_topology_data_viewset(viewsets.ModelViewSet):
    queryset = us_lab_topology_data.objects.all()
    serializer_class = us_lab_topology_data_serializer

class us_lab_settings_viewset(viewsets.ModelViewSet):
    queryset = us_lab_settings_data.objects.all()
    serializer_class = us_lab_settings_serializer


