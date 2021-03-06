# -*- coding: utf-8 -*-
# Generated by Django 1.11 on 2017-04-19 05:58
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('app_topo', '0009_us_lab_resource_plan_data'),
    ]

    operations = [
        migrations.AlterField(
            model_name='us_lab_resource_plan_data',
            name='description',
            field=models.TextField(blank=True),
        ),
        migrations.AlterField(
            model_name='us_lab_resource_plan_data',
            name='gateway',
            field=models.GenericIPAddressField(blank=True, null=True),
        ),
        migrations.AlterField(
            model_name='us_lab_resource_plan_data',
            name='ip_prefix',
            field=models.IntegerField(blank=True),
        ),
        migrations.AlterField(
            model_name='us_lab_resource_plan_data',
            name='maxHaId',
            field=models.IntegerField(blank=True),
        ),
        migrations.AlterField(
            model_name='us_lab_resource_plan_data',
            name='maxIp',
            field=models.GenericIPAddressField(blank=True, null=True),
        ),
        migrations.AlterField(
            model_name='us_lab_resource_plan_data',
            name='maxOspfId',
            field=models.IntegerField(blank=True),
        ),
        migrations.AlterField(
            model_name='us_lab_resource_plan_data',
            name='maxVlan',
            field=models.IntegerField(blank=True),
        ),
        migrations.AlterField(
            model_name='us_lab_resource_plan_data',
            name='minHaId',
            field=models.IntegerField(blank=True),
        ),
        migrations.AlterField(
            model_name='us_lab_resource_plan_data',
            name='minIp',
            field=models.GenericIPAddressField(blank=True, null=True),
        ),
        migrations.AlterField(
            model_name='us_lab_resource_plan_data',
            name='minOspfId',
            field=models.IntegerField(blank=True),
        ),
        migrations.AlterField(
            model_name='us_lab_resource_plan_data',
            name='minVlan',
            field=models.IntegerField(blank=True),
        ),
    ]
