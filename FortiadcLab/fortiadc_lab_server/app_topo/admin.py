# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.contrib import admin

# Register your models here.

from models import us_lab_topology_data
from models import us_lab_settings_data
from models import us_lab_resource_plan_data

admin.site.register(us_lab_topology_data)
admin.site.register(us_lab_settings_data)
admin.site.register(us_lab_resource_plan_data)


