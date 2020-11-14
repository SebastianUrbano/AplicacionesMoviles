"# IziMath" 
"# IziMath" 



FROM ubuntu:latest
MAINTAINER sebastian "john.urbano@correo.icesi.edu.co"

ENV DEBIAN_FRONTEND=noninteractive


WORKDIR /usr/PDG
ADD . ./archivos

RUN apt-get update \
  && apt-get install -y python3-pip python3-dev \
  && cd /usr/local/bin \
  && ln -s /usr/bin/python3 python \
  && pip3 install --upgrade pip 


ENTRYPOINT ["python3"]

RUN  sudo apt install postgresql postgresql-contrib \
  && cd /usr/PDG/ \
  && git clone https://github.com/mauriciosedano/opx-opensource-web.git \
  && cp -R /archivos/settings.py /opx-opensource-web/opc/ \
  && cp -R /archivos/models.py /opx-opensource-web/myapp/ \
  && sudo -u postgres psql \
  && CREATE USER opxuser WITH PASSWORD 'opx_dev_pass' CREATEDB; \
  && CREATE DATABASE opx_dev; \
  && ALTER DATABASE opx_dev OWNER TO opxuser; \
  && \c opx_dev \
  && CREATE SCHEMA v1; \
  && alter schema v1 owner to opxuser; \
  && \q \
  && cd /opx-opensource-web \
  && python3 -m venv opx_test \
  && source opx_test/bin/activate \
  && pip install -r requirements.txt \
  && python3 manage.py makemigrations
  && python3 manage.py migrate
  && sudo -u postgres psql \
  && \c opx_dev \
  && \i /usr/PDG/archivos/carga-ddl.sql \
  && \q \
  && source opx_test/bin/activate
EXPOSE 8000
EXPOSE 5432
CMD ["python manage.py runserver", "-D", "FOREGROUND"] 
  
