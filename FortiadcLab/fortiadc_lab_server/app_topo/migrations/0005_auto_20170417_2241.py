# -*- coding: utf-8 -*-
# Generated by Django 1.11 on 2017-04-17 22:41
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('app_topo', '0004_auto_20170417_1911'),
    ]

    operations = [
        migrations.AlterField(
            model_name='us_lab_settings_data',
            name='id',
            field=models.AutoField(primary_key=True, serialize=False),
        ),
    ]
