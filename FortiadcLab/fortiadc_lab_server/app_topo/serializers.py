
from app_topo.models import us_lab_topology_data
from app_topo.models import us_lab_settings_data
from app_topo.models import us_lab_resource_plan_data
from app_topo.models import us_lab_resource_plan_ip_address
from app_topo.models import us_lab_resource_plan_ha_id
from app_topo.models import us_lab_resource_plan_vlan
# from models import us_lab_resource_plan_ospf_id
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

class us_lab_resource_plan_ip_address_serializer(serializers.ModelSerializer):
    class Meta:
        model = us_lab_resource_plan_ip_address
        fields = ('id', 'name', 'minIp', 'maxIp', 'ip_prefix', 'gateway', 'description')

class us_lab_resource_plan_ha_id_serializer(serializers.ModelSerializer):
    class Meta:
        model = us_lab_resource_plan_ha_id
        fields = ('id', 'name', 'minHaId', 'maxHaId', 'description')

class us_lab_resource_plan_vlan_serializer(serializers.ModelSerializer):
    class Meta:
        model = us_lab_resource_plan_vlan
        fields = ('id', 'name', 'minVlan', 'maxVlan', 'description')

# class us_lab_resource_plan_ospf_id_serializer(serializers.ModelSerializer):
#     class Meta:
#         model = us_lab_resource_plan_ospf_id
#         fields = ('id', 'name', 'minOspfId', 'maxOspfId', 'description')

