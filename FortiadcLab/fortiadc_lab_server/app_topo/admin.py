# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.contrib import admin

# Register your models here.

from app_topo.models import us_lab_topology_data
from app_topo.models import us_lab_settings_data
from app_topo.models import us_lab_resource_plan_data
from app_topo.models import us_lab_resource_plan_ip_address
from app_topo.models import us_lab_resource_plan_ha_id
from app_topo.models import us_lab_resource_plan_vlan
# from models import us_lab_resource_plan_ospf_id

admin.site.register(us_lab_topology_data)
admin.site.register(us_lab_settings_data)
admin.site.register(us_lab_resource_plan_data)
admin.site.register(us_lab_resource_plan_ip_address)
admin.site.register(us_lab_resource_plan_ha_id)
admin.site.register(us_lab_resource_plan_vlan)
# admin.site.register(us_lab_resource_plan_ospf_id)


