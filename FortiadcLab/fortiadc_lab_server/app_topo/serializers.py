
from models import us_lab_topology_data
from models import us_lab_settings_data
from models import us_lab_resource_plan_data
from rest_framework import serializers


from django.contrib.auth.models import User, Group


class us_lab_topology_data_serializer(serializers.ModelSerializer):
    class Meta:
        model = us_lab_topology_data
        fields = ('name', 'type', 'content')

class us_lab_settings_serializer(serializers.ModelSerializer):
    class Meta:
        model = us_lab_settings_data
        fields = ('key', 'value')

class us_lab_resource_plan_serializer(serializers.ModelSerializer):
    class Meta:
        model = us_lab_resource_plan_data
        fields = ('id', 'name', 'minIp', 'maxIp', 'ip_prefix', 'gateway', 'minHaId', 'maxHaId',
                  'minVlan', 'maxVlan', 'minOspfId', 'maxOspfId', 'description')



