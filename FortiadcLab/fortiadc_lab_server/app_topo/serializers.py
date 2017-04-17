
from models import us_lab_topology_data
from rest_framework import serializers

from django.contrib.auth.models import User, Group


class us_lab_topology_data_serializer(serializers.ModelSerializer):
    class Meta:
        model = us_lab_topology_data
        fields = ('name', 'type', 'content')




