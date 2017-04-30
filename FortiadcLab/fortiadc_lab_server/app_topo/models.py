# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models

# Create your models here.


class us_lab_topology_data(models.Model):
    name = models.CharField(max_length=30, unique=True, primary_key=True)
    type = models.CharField(max_length=10)
    content = models.TextField()

    def __unicode__(self):
        return self.name


class us_lab_settings_data(models.Model):
    # id = models.AutoField(primary_key = True)
    key = models.CharField(max_length=50, primary_key=True, unique=True)
    value = models.TextField()

    def __unicode__(self):
        return self.key


class us_lab_resource_plan_data(models.Model):
    id = models.AutoField(primary_key=True, unique=True)
    name = models.CharField(max_length=50)
    minIp = models.GenericIPAddressField(blank=True, null=True)
    maxIp = models.GenericIPAddressField(blank=True, null=True)
    ip_prefix = models.IntegerField(blank=True, null=True)
    gateway = models.GenericIPAddressField(blank=True, null=True)
    minHaId = models.IntegerField(blank=True, null=True)
    maxHaId = models.IntegerField(blank=True, null=True)
    minVlan = models.IntegerField(blank=True, null=True)
    maxVlan = models.IntegerField(blank=True, null=True)
    minOspfId = models.IntegerField(blank=True, null=True)
    maxOspfId = models.IntegerField(blank=True, null=True)
    description = models.TextField(blank=True)

    def __unicode__(self):
        return str(self.id)

class us_lab_resource_plan_ip_address(models.Model):
    id = models.AutoField(primary_key=True, unique=True)
    name = models.CharField(max_length=50)
    minIp = models.GenericIPAddressField(blank=True, null=True)
    maxIp = models.GenericIPAddressField(blank=True, null=True)
    ip_prefix = models.IntegerField(blank=True, null=True)
    gateway = models.GenericIPAddressField(blank=True, null=True)
    description = models.TextField(blank=True)

    def __unicode__(self):
        return str(self.name)

class us_lab_resource_plan_ha_id(models.Model):
    id = models.AutoField(primary_key=True, unique=True)
    name = models.CharField(max_length=50)
    minHaId = models.IntegerField(blank=True, null=True)
    maxHaId = models.IntegerField(blank=True, null=True)
    description = models.TextField(blank=True)

    def __unicode__(self):
        return str(self.name)

class us_lab_resource_plan_vlan(models.Model):
    id = models.AutoField(primary_key=True, unique=True)
    name = models.CharField(max_length=50)
    minVlan = models.IntegerField(blank=True, null=True)
    maxVlan = models.IntegerField(blank=True, null=True)
    description = models.TextField(blank=True)

    def __unicode__(self):
        return str(self.name)

# class us_lab_resource_plan_ospf_id(models.Model):
#     id = models.AutoField(primary_key=True, unique=True)
#     name = models.CharField(max_length=50)
#     minOspfId = models.IntegerField(blank=True, null=True)
#     maxOspfId = models.IntegerField(blank=True, null=True)
#     description = models.TextField(blank=True)
#
#     def __unicode__(self):
#         return str(self.name)







