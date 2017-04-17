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
    id = models.AutoField(primary_key = True)
    key = models.CharField(max_length=50)
    value = models.TextField()

    def __unicode__(self):
        return self.key





