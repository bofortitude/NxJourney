# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models

# Create your models here.

class us_lab_topology_data(models.Model):
    name = models.CharField(max_length=30, unique=True, primary_key=True)
    type = models.CharField(max_length=10)
    content = models.TextField()

