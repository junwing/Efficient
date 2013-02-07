------------------------------------------------------------------------
-- SEQUENCE: {name}
-- {description}
--
-- ����޸��ˣ�
-- ����޸����ڣ�{date}
------------------------------------------------------------------------
DROP SEQUENCE {name};

CREATE SEQUENCE {name}
  START WITH {minValue}
  MAXVALUE {maxValue}
  NOCYCLE
  CACHE 20
  ORDER;